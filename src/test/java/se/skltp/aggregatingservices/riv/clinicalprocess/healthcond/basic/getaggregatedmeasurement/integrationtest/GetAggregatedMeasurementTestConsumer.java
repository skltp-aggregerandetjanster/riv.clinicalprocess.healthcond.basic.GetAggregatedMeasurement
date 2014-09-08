package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement.integrationtest;

import static se.skltp.agp.test.producer.TestProducerDb.TEST_RR_ID_ONE_HIT;

import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.riv.clinicalprocess.healthcond.basic.getmeasurement.v1.rivtabp21.GetMeasurementResponderInterface;
import se.riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementResponseType;
import se.riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementType;
import se.riv.clinicalprocess.healthcond.basic.v1.IIType;
import se.riv.clinicalprocess.healthcond.basic.v1.TimePeriodType;
import se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement.GetAggregatedMeasurementMuleServer;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;
import se.skltp.agp.test.consumer.AbstractTestConsumer;
import se.skltp.agp.test.consumer.SoapHeaderCxfInterceptor;

public class GetAggregatedMeasurementTestConsumer extends AbstractTestConsumer<GetMeasurementResponderInterface> {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedMeasurementTestConsumer.class);

	public static void main(String[] args) {
		String serviceAddress = GetAggregatedMeasurementMuleServer.getAddress("SERVICE_INBOUND_URL");
		String personnummer = TEST_RR_ID_ONE_HIT;

		GetAggregatedMeasurementTestConsumer consumer = new GetAggregatedMeasurementTestConsumer(serviceAddress, SAMPLE_SENDER_ID, SAMPLE_ORIGINAL_CONSUMER_HSAID);
		Holder<GetMeasurementResponseType> responseHolder = new Holder<GetMeasurementResponseType>();
		Holder<ProcessingStatusType> processingStatusHolder = new Holder<ProcessingStatusType>();

		consumer.callService("logical-adress", personnummer, processingStatusHolder, responseHolder);
		log.info("Returned #timeslots = " + responseHolder.value.getMeasurement().size());
	}

	public GetAggregatedMeasurementTestConsumer(String serviceAddress, String senderId, String originalConsumerHsaId) {
	    
		// Setup a web service proxy for communication using HTTPS with Mutual Authentication
		super(GetMeasurementResponderInterface.class, serviceAddress, senderId, originalConsumerHsaId);
	}
	
	public void callService(String logicalAddress, String registeredResidentId, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetMeasurementResponseType> responseHolder){
		callService(logicalAddress, registeredResidentId, null, null, processingStatusHolder, responseHolder);
	}

	public void callService(String logicalAddress, String registeredResidentId, String starTime, String endTime, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetMeasurementResponseType> responseHolder) {

		log.debug("Calling GetRequestActivities-soap-service with Registered Resident Id = {}", registeredResidentId);
		
		GetMeasurementType request = new GetMeasurementType();

		// TODO: CHANGE GENERATED CODE - START
		
		request.setPatientId(createID(registeredResidentId));
		
		request.setTime(createTimePeriodType(starTime, endTime));
		
		request.setSourceSystemId(createSourceSystemId(logicalAddress));

		// TODO: CHANGE GENERATED CODE - END


		GetMeasurementResponseType response = _service.getMeasurement(logicalAddress, request);
		responseHolder.value = response;
		
		processingStatusHolder.value = SoapHeaderCxfInterceptor.getLastFoundProcessingStatus();
	}

	private IIType createSourceSystemId(String logicalAddress) {
		IIType iiType = new IIType();
		iiType.setExtension(logicalAddress);
		iiType.setRoot("1.2.752.129.2.1.4.1");
		return iiType;
	}

	private TimePeriodType createTimePeriodType(String starTime, String endTime) {
		TimePeriodType periodType = new TimePeriodType();
		periodType.setEnd(endTime);
		periodType.setStart(starTime);
		return periodType;
	}

	private IIType createID(String registeredResidentId) {
		IIType iiType = new IIType();
		
		// TKB 6.4.2 Fältregler. För personnummer används OID (1.2.752.129.2.1.3.1).
		iiType.setRoot("1.2.752.129.2.1.3.1");
		iiType.setExtension(registeredResidentId);
		
		return iiType;
	}
}