package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.jaxb.JaxbUtil;
import org.w3c.dom.Node;

import se.riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.QueryObjectFactory;

public class QueryObjectFactoryImpl implements QueryObjectFactory {

	private static final Logger log = LoggerFactory.getLogger(QueryObjectFactoryImpl.class);
	private static final JaxbUtil ju = new JaxbUtil(GetMeasurementType.class);

	private String eiServiceDomain;
	public void setEiServiceDomain(String eiServiceDomain) {
		this.eiServiceDomain = eiServiceDomain;
	}

	private String eiCategorization;
	public void setEiCategorization(String eiCategorization) {
		this.eiCategorization = eiCategorization;
	}

	/**
	 * Transformerar GetMeasurement request till EI FindContent request enligt:
	 * 
	 * 1. patientId --> registeredResidentIdentification
	 * 2. riv:clinicalprocess:healthcond:basic --> serviceDomain
	 * 3. chb-gm --> categorization
	 * 4. sourceSystemId.extension --> sourceSystem
	 */
	@Override
	public QueryObject createQueryObject(Node node) {
		
		GetMeasurementType request = (GetMeasurementType)ju.unmarshal(node);
		
		if (log.isDebugEnabled()) log.debug("Transformed payload for pid: {}", request.getPatientId().getExtension());

		FindContentType fc = new FindContentType();		
		fc.setRegisteredResidentIdentification(request.getPatientId().getExtension());
		fc.setServiceDomain(eiServiceDomain);
		fc.setCategorization(eiCategorization);
		fc.setSourceSystem(request.getSourceSystemId().getExtension());
		
		QueryObject qo = new QueryObject(fc, request);

		return qo;
	}
}
