package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;

import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementResponseType;
import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.ObjectFactory;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.ResponseListFactory;

public class ResponseListFactoryImpl implements ResponseListFactory {

	private static final Logger log = LoggerFactory.getLogger(ResponseListFactoryImpl.class);
	private static final JaxbUtil jaxbUtil = new JaxbUtil(GetMeasurementResponseType.class, ProcessingStatusType.class);
	private static final ObjectFactory OF = new ObjectFactory();
	
	@Override
	public String getXmlFromAggregatedResponse(QueryObject queryObject, List<Object> aggregatedResponseList) {

	    log.info("getXmlFromAggregatedResponse");
		GetMeasurementResponseType aggregatedResponse = new GetMeasurementResponseType();

	    for (Object object : aggregatedResponseList) {
	    	GetMeasurementResponseType response = (GetMeasurementResponseType)object;
			aggregatedResponse.getMeasurement().addAll(response.getMeasurement());
		}

	    if (log.isInfoEnabled()) {
    		String patientId = queryObject.getFindContent().getRegisteredResidentIdentification();
        	log.info("Returning {} aggregated measurements for patient id {}", aggregatedResponse.getMeasurement().size(), patientId);
        }
        
        // Since the class GetRequestActivitiesResponseType doesn't have an @XmlRootElement annotation
        // we need to use the ObjectFactory to add it.
        return jaxbUtil.marshal(OF.createGetMeasurementResponse(aggregatedResponse));
	}
}