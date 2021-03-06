package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement.integrationtest;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import riv.clinicalprocess.healthcond.basic.getmeasurement.v1.rivtabp21.GetMeasurementResponderInterface;
import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementResponseType;
import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementType;
import se.skltp.agp.test.producer.TestProducerDb;

@WebService(serviceName = "GetMeasurementResponderService", 
    		   portName = "GetMeasurementResponderPort", 
	    targetNamespace = "urn:riv:clinicalprocess:healthcond:basic:GetMeasurement:1:rivtabp21", 
		           name = "GetMeasurementInteraction")
public class GetAggregatedMeasurementTestProducer implements GetMeasurementResponderInterface {

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedMeasurementTestProducer.class);

	private TestProducerDb testDb;
	public void setTestDb(TestProducerDb testDb) {
		this.testDb = testDb;
	}


    public GetMeasurementResponseType getMeasurement(String logicalAddress, GetMeasurementType request) {
        final Object response = testDb.processRequest(logicalAddress, request.getPatientId().getExtension());
        if (response == null) {
            return new GetMeasurementResponseType();
        }
        return (GetMeasurementResponseType) response;
    }
	
	/*
	@Override
	@WebResult(name = "GetMeasurementResponse", targetNamespace = "urn:riv:clinicalprocess:healthcond:basic:GetMeasurementResponder:1", partName = "parameters")
	@WebMethod(operationName = "GetMeasurement", action = "urn:riv:clinicalprocess:healthcond_basic:GetMeasurementResponder:1:GetMeasurement")
	public GetMeasurementResponseType getMeasurement(
			@WebParam(partName = "LogicalAddress", name = "LogicalAddress", targetNamespace = "urn:riv:itintegration:registry:1", header = true) String logicalAddress,
			@WebParam(partName = "parameters", name = "GetMeasurement", targetNamespace = "urn:riv:clinicalprocess:healthcond:basic:GetMeasurementResponder:1") GetMeasurementType request) {
		
		log.info("### Virtual service for GetMeasurement call the source system with logical address: {} and patientId: {}", logicalAddress, request.getPatientId().getExtension());

		GetMeasurementResponseType response = (GetMeasurementResponseType)testDb.processRequest(logicalAddress, request.getPatientId().getExtension());
        if (response == null) {
        	// Return an empty response object instead of null if nothing is found
        	response = new GetMeasurementResponseType();
        }

		log.info("### Virtual service got {} measurements in the reply from the source system with logical address: {} and patientId: {}", 
				new Object[] {response.getMeasurement().size(), logicalAddress, request.getPatientId().getExtension()});

		// We are done
        return response;
	}
	*/
}