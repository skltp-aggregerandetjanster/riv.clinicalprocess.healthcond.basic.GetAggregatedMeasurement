package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement.integrationtest;

import static se.skltp.agp.test.producer.TestProducerDb.TEST_RR_ID_ONE_HIT;

import javax.xml.ws.Holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.basic.getmeasurement.v1.rivtabp21.GetMeasurementResponderInterface;
import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementResponseType;
import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementType;
import riv.clinicalprocess.healthcond.basic.v1.IIType;
import se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement.GetAggregatedMeasurementMuleServer;
import se.skltp.agp.riv.interoperability.headers.v1.ProcessingStatusType;
import se.skltp.agp.test.consumer.AbstractTestConsumer;
import se.skltp.agp.test.consumer.SoapHeaderCxfInterceptor;

public class GetAggregatedMeasurementTestConsumer extends AbstractTestConsumer<GetMeasurementResponderInterface> {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedMeasurementTestConsumer.class);

	public static void main(String[] args) {
		String serviceAddress = GetAggregatedMeasurementMuleServer.getAddress("SERVICE_INBOUND_URL");
		String personnummer = TEST_RR_ID_ONE_HIT;

		GetAggregatedMeasurementTestConsumer consumer = new GetAggregatedMeasurementTestConsumer(serviceAddress, SAMPLE_SENDER_ID, SAMPLE_ORIGINAL_CONSUMER_HSAID, SAMPLE_CORRELATION_ID);
		Holder<GetMeasurementResponseType> responseHolder = new Holder<GetMeasurementResponseType>();
		Holder<ProcessingStatusType> processingStatusHolder = new Holder<ProcessingStatusType>();

		consumer.callService("logical-adress", personnummer, processingStatusHolder, responseHolder);
		log.info("Returned #measurements = " + responseHolder.value.getMeasurement().size());
	}

	public GetAggregatedMeasurementTestConsumer(String serviceAddress, String senderId, String originalConsumerHsaId, String correlationId) {
	    
		// Setup a web service proxy for communication using HTTPS with Mutual Authentication
		super(GetMeasurementResponderInterface.class, serviceAddress, senderId, originalConsumerHsaId, correlationId);
	}
	
	public void callService(String logicalAddress, String registeredResidentId, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetMeasurementResponseType> responseHolder){
		callService(logicalAddress, registeredResidentId, null, null, processingStatusHolder, responseHolder);
	}

	public void callService(String logicalAddress, String registeredResidentId, String starTime, String endTime, Holder<ProcessingStatusType> processingStatusHolder, Holder<GetMeasurementResponseType> responseHolder) {
		log.debug("Calling GetMeasurement-soap-service with Registered Resident Id = {}", registeredResidentId);
		
		GetMeasurementType request = new GetMeasurementType();
		/*
		request.setSourceSystemId(new IIType());
		request.getSourceSystemId().setExtension(logicalAddress);
		*/
		
		request.setPatientId(new IIType());
		request.getPatientId().setExtension(registeredResidentId);

		GetMeasurementResponseType response = _service.getMeasurement(logicalAddress, request);
		responseHolder.value = response;
		
		processingStatusHolder.value = SoapHeaderCxfInterceptor.getLastFoundProcessingStatus();
	}
	
}