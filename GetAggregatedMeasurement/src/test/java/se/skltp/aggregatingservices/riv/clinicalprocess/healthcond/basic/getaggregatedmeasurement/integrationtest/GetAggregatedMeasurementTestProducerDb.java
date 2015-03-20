package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement.integrationtest;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.util.ThreadSafeSimpleDateFormat;

import riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementResponseType;
import riv.clinicalprocess.healthcond.basic.v1.CVType;
import riv.clinicalprocess.healthcond.basic.v1.DeviceType;
import riv.clinicalprocess.healthcond.basic.v1.IIType;
import riv.clinicalprocess.healthcond.basic.v1.LegalAuthenticatorType;
import riv.clinicalprocess.healthcond.basic.v1.LocationType;
import riv.clinicalprocess.healthcond.basic.v1.MeasurementType;
import riv.clinicalprocess.healthcond.basic.v1.PQType;
import riv.clinicalprocess.healthcond.basic.v1.PatientType;
import riv.clinicalprocess.healthcond.basic.v1.PerformerRoleType;
import riv.clinicalprocess.healthcond.basic.v1.SourceSystemType;
import riv.clinicalprocess.healthcond.basic.v1.TimePeriodType;
import se.skltp.agp.test.producer.TestProducerDb;

public class GetAggregatedMeasurementTestProducerDb extends TestProducerDb {
	
	ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("YYYYMMddHHmmss");

	private static final Logger log = LoggerFactory.getLogger(GetAggregatedMeasurementTestProducerDb.class);
	
	@Override
	public Object processRequest(String logicalAddress,
			String registeredResidentId) {
		
		/*
		 *  Return an error-message if error logical address HSA-ID-6 is called.
		 *  
		 *  This case was added to be able to test the case when a system returns an error for a certain
		 *  RR_ID and another system returns a OK response. This case should probably be added to the
		 *  agp test common TestProducerDb, but for know its included here to test the concept.
		 */
		if (TEST_LOGICAL_ADDRESS_6.equals(logicalAddress)) {
			throw new RuntimeException("Logical address to trigger exception was called: " + logicalAddress);
		}
		
		return super.processRequest(logicalAddress, registeredResidentId);
	}

	@Override
	public Object createResponse(Object... responseItems) {
		log.debug("Creates a response with {} items", responseItems);
		GetMeasurementResponseType response = new GetMeasurementResponseType();
		for (int i = 0; i < responseItems.length; i++) {
			response.getMeasurement().add((MeasurementType)responseItems[i]);
		}
		return response;
	}
	
	@Override
	public Object createResponseItem(String logicalAddress, String registeredResidentId, String businessObjectId, String time) {
		
		if (log.isDebugEnabled()) {
			log.debug("Created one response item for logical-address {}, registeredResidentId {} and businessObjectId {}",
				new Object[] {logicalAddress, registeredResidentId, businessObjectId});
		}

		MeasurementType response = new MeasurementType();

		// TODO: CHANGE GENERATED CODE - START
		
		response.setApprovedForPatient(true);
		
		//MeasurementType
		response.setId(createMeasurementTypeId(logicalAddress));
		response.setType(createMeasurementTypeType(logicalAddress));
		response.setTime(createMeasurementTypeTime());
		response.setValue(createMeasurementTypeValue());
		
		//PatientType
		response.setPatient(createPatient(registeredResidentId));

		//PerformerRoleType
		response.setPerformerRole(createPerformerRole());
		
		response.setDevice(createDevice("Modelname"));
		response.setLegalAuthenticator(createLegalAuthenticator("19791017160000","Kalle Kula"));
		response.setLocation(createLocaltion("Provtagningsgatan 9"));
		response.setMethod(createCVType());
		response.setSourceSystem(createSourceSystem());
		response.setTargetSite(createCVType());
		
		// TODO: CHANGE GENERATED CODE - END
		
		return response;
	}

	private SourceSystemType createSourceSystem() {
		SourceSystemType sourceSystemType = new SourceSystemType();
		sourceSystemType.setId(createId("1.2.752.129.2.1.4.1", "HSA-ID"));
		return sourceSystemType;
	}

	private LocationType createLocaltion(String name) {
		LocationType locationType = new LocationType();
		locationType.setId(createId("1.2.752.129.2.1.4.1", "HSA-ID"));
		locationType.setName(name);
		return locationType;
	}

	private LegalAuthenticatorType createLegalAuthenticator(String time, String name) {
		LegalAuthenticatorType authenticatorType = new LegalAuthenticatorType();
		authenticatorType.setId(createId("1.2.752.129.2.1.4.1", "HSA-ID"));
		authenticatorType.setName(name);
		authenticatorType.setTime(time);
		return authenticatorType;
	}

	private DeviceType createDevice(String modelName) {
		DeviceType deviceType = new DeviceType();
		deviceType.setId(createId("1.3.160", "GSIN NR"));
		deviceType.setManufacturerModelName(modelName);
		deviceType.setType(createCVType());
		return deviceType;
	}

	private PerformerRoleType createPerformerRole() {
		PerformerRoleType performerRoleType = new PerformerRoleType();
		performerRoleType.setCode(createCVType());
		return performerRoleType;
	}

	private CVType createCVType() {
		CVType cvType = new CVType();
		cvType.setCode("CODE");
		cvType.setCodeSystem("CODESYSTEM");
		return cvType;
	}

	private PatientType createPatient(String registeredResidentId) {
		PatientType patientType = new PatientType();
		patientType.setId(createPatientId(registeredResidentId));
		patientType.setDateOfBirth(registeredResidentId.substring(0, 8));
		return patientType;
	}

	private IIType createPatientId(String registeredResidentId) {
		return createId("1.2.752.129.2.1.3.1", registeredResidentId);
	}
	
	private IIType createId(String root, String extension) {
		IIType iiType = new IIType();
		iiType.setRoot(root);
		iiType.setExtension(extension);
		return iiType;
	}

	private PQType createMeasurementTypeValue() {
		PQType pqType = new PQType();
		pqType.setValue(2L);
		//Enhet enligt standard http://unitsofmeasure.org/ucum.html
		pqType.setUnit("British thermal unit ");
		return pqType;
	}

	private TimePeriodType createMeasurementTypeTime() {
		TimePeriodType periodType = new TimePeriodType();
		periodType.setStart(sdf.format(new Date()));
		return periodType;
	}

	private CVType createMeasurementTypeType(String logicalAddress) {
		CVType cvType = new CVType();
		cvType.setCode("CODE");
		cvType.setCodeSystem("CODESYSTEM");
		return cvType;
	}

	private IIType createMeasurementTypeId(String logicalAddress) {
		
		IIType iiType = new IIType();
		iiType.setExtension(logicalAddress + ":" + UUID.randomUUID());
		iiType.setRoot("1.2.752.129.2.1.2.1");
		return iiType;
	}
}