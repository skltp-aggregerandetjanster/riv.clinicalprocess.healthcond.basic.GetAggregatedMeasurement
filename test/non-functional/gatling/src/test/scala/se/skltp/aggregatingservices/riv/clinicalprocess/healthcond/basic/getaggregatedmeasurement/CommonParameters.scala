package se.skltp.aggregatingservices.riv.clinicalprocess.healthcond.basic.getaggregatedmeasurement

trait CommonParameters {
  val serviceName:String     = "Measurement"
  val urn:String             = "urn:riv:clinicalprocess:healthcond_basic:GetMeasurementResponder:1"
  val responseElement:String = "GetMeasurementResponse"
  val responseItem:String    = "measurement"
  val responseItemUrn:String = "urn:riv:clinicalprocess:healthcond:basic:GetMeasurementResponder:1"
  var baseUrl:String         = if (System.getProperty("baseUrl") != null && !System.getProperty("baseUrl").isEmpty()) {
                                   System.getProperty("baseUrl")
                               } else {
                                   "http://33.33.33.33:8081/GetAggregatedMeasurement/service/v1"
                               }
}
