package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.soitoolkit.commons.mule.util.ThreadSafeSimpleDateFormat;

import se.riv.clinicalprocess.healthcond.basic.getmeasurementresponder.v1.GetMeasurementType;
import se.skltp.agp.riv.itintegration.engagementindex.findcontentresponder.v1.FindContentResponseType;
import se.skltp.agp.riv.itintegration.engagementindex.v1.EngagementType;
import se.skltp.agp.service.api.QueryObject;
import se.skltp.agp.service.api.RequestListFactory;

public class RequestListFactoryImpl implements RequestListFactory {

	private static final Logger log = LoggerFactory.getLogger(RequestListFactoryImpl.class);
	private static final ThreadSafeSimpleDateFormat df = new ThreadSafeSimpleDateFormat("YYYYMMDDhhmmss");

	/**
	 * Filtrera svarsposter från i EI (ei-engagement) baserat parametrar i GetMeasurement requestet (req).
	 * Följande villkor måste vara sanna för att en svarspost från EI skall tas med i svaret:
	 * 
	 * 1. req.getSourceSystemId == null or req.getSourceSystemId == "" or req.getSourceSystemId == ei-engagement.logicalAddress
	 * 
	 * Svarsposter från EI som passerat filtreringen grupperas på fältet sourceSystem samt postens fält logicalAddress (= PDL-enhet) samlas i listan careUnitId per varje sourceSystem
	 * 
	 * Ett anrop görs per funnet sourceSystem med följande värden i anropet:
	 * 
	 * 1. logicalAddress = sourceSystem (systemadressering)
	 * 2. request = originalRequest (ursprungligt anrop från konsument)
	 */
	@Override
	public List<Object[]> createRequestList(QueryObject qo, FindContentResponseType src) {

		GetMeasurementType originalRequest = (GetMeasurementType)qo.getExtraArg();
		// TODO: CHANGE GENERATED CODE - START
		//Date reqFrom = parseTs(originalRequest.getFromDate());
		//Date reqTo   = parseTs(originalRequest.getToDate());
		String reqCareUnit = originalRequest.getSourceSystemId().getExtension();
		// TODO: CHANGE GENERATED CODE - END

		FindContentResponseType eiResp = (FindContentResponseType)src;
		List<EngagementType> inEngagements = eiResp.getEngagement();
		
		log.info("Got {} hits in the engagement index", inEngagements.size());

		Map<String, List<String>> sourceSystem_pdlUnitList_map = new HashMap<String, List<String>>();
		
		for (EngagementType inEng : inEngagements) {

			// Filter
			// TODO: CHANGE GENERATED CODE - START
			//if (isBetween(reqFrom, reqTo, inEng.getMostRecentContent()) &&
			//	isPartOf(reqCareUnitList, inEng.getLogicalAddress())) {
			
			//TKB 4.1 Uppdatering av engagemangsindex
			//LogicalAddress: Samma värde som fältet Source System.
			
			if (isPartOf(reqCareUnit, inEng.getLogicalAddress())) {
			// TODO: CHANGE GENERATED CODE - END

				// Add pdlUnit to source system
				log.debug("Add source system: {} for PDL unit: {}", inEng.getSourceSystem(), inEng.getLogicalAddress());
				addPdlUnitToSourceSystem(sourceSystem_pdlUnitList_map, inEng.getSourceSystem(), inEng.getLogicalAddress());
			}
		}

		// Prepare the result of the transformation as a list of request-payloads, 
		// one payload for each unique logical-address (e.g. source system since we are using systemaddressing),
		// each payload built up as an object-array according to the JAX-WS signature for the method in the service interface
		List<Object[]> reqList = new ArrayList<Object[]>();
		
		for (Entry<String, List<String>> entry : sourceSystem_pdlUnitList_map.entrySet()) {

			String sourceSystem = entry.getKey();

			if (log.isInfoEnabled()) log.info("Calling source system using logical address {} for patient id {}", sourceSystem, originalRequest.getPatientId().getExtension());

			// TODO: CHANGE GENERATED CODE - START
			GetMeasurementType request = originalRequest;
			// TODO: CHANGE GENERATED CODE - END
			
			Object[] reqArr = new Object[] {sourceSystem, request};
			
			reqList.add(reqArr);
		}

		log.debug("Transformed payload: {}", reqList);

		return reqList;
	}

//	Date parseTs(String ts) {
//		try {
//			if (ts == null || ts.length() == 0) {
//				return null;
//			} else {
//				return df.parse(ts);
//			}
//		} catch (ParseException e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	boolean isBetween(Date from, Date to, String tsStr) {
//		try {
//			if (log.isDebugEnabled()) {
//				log.debug("Is {} between {} and ", new Object[] {tsStr, from, to});
//			}
//			
//			Date ts = df.parse(tsStr);
//			if (from != null && from.after(ts)) return false;
//			if (to != null && to.before(ts)) return false;
//			return true;
//		} catch (ParseException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	boolean isPartOf(String careUnitId, String careUnit) {
		
		log.debug("Check careunit {} equals expected {}", careUnitId, careUnit);
		
		if (StringUtils.isBlank(careUnitId)) return true;
		
		return careUnitId.equals(careUnit);
	}

	void addPdlUnitToSourceSystem(Map<String, List<String>> sourceSystem_pdlUnitList_map, String sourceSystem, String pdlUnitId) {
		List<String> careUnitList = sourceSystem_pdlUnitList_map.get(sourceSystem);
		if (careUnitList == null) {
			careUnitList = new ArrayList<String>();
			sourceSystem_pdlUnitList_map.put(sourceSystem, careUnitList);
		}
		careUnitList.add(pdlUnitId);
	}
}