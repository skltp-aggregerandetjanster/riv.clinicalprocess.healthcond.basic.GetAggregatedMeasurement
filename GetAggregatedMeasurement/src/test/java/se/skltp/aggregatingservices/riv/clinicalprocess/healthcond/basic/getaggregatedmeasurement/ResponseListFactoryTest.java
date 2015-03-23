package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;

import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementResponseType;
import riv.clinicalprocess.healthcond.basic.v1.IIType;
import riv.clinicalprocess.healthcond.basic.v1.MeasurementType;
import riv.clinicalprocess.healthcond.basic.v1.PatientType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentType;
import se.skltp.agp.service.api.QueryObject;

public class ResponseListFactoryTest {

        private final static String SUBJECT_OF_CARE = UUID.randomUUID().toString();
        private final static int NUMBER_OF_RESPONSES = 5;
        
        private final ResponseListFactoryImpl testObject = new ResponseListFactoryImpl();
        private final List<Object> responseList = new ArrayList<Object>();
        
        private final QueryObject queryObject = Mockito.mock(QueryObject.class);
        private final FindContentType findContentType = Mockito.mock(FindContentType.class);
        
        @Before
        public void setup() {
            Mockito.when(findContentType.getRegisteredResidentIdentification()).thenReturn(SUBJECT_OF_CARE);
            Mockito.when(queryObject.getFindContent()).thenReturn(findContentType);
            
            for(int i = 0; i < NUMBER_OF_RESPONSES; i++) {
                final GetMeasurementResponseType resp = new GetMeasurementResponseType();
                final MeasurementType ref = new MeasurementType();
                ref.setPatient(new PatientType());
                ref.getPatient().setId(new IIType());
                ref.getPatient().getId().setExtension(SUBJECT_OF_CARE);
                resp.getMeasurement().add(ref);
                responseList.add(resp);
            }
        }

        @Test
        public void testGetXmlFromAggregatedResponse() {
            final JaxbUtil jaxbUtil = new JaxbUtil(GetMeasurementResponseType.class);

            final String after = testObject.getXmlFromAggregatedResponse(queryObject, responseList);
            final GetMeasurementResponseType type = (GetMeasurementResponseType) jaxbUtil.unmarshal(after);
            
            assertEquals(NUMBER_OF_RESPONSES, type.getMeasurement().size());
            for(MeasurementType ref : type.getMeasurement()) {
                assertEquals(SUBJECT_OF_CARE, ref.getPatient().getId().getExtension());
            }
        }
}
