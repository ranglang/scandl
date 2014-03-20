package com.scandl
import scalaj.http.Http

case class RequestContext(startDate:Option[String] = None, endDate:Option[String] = None, frequency:Option[String] = None)

trait ApiAccessComponent {
	val apiAccess: ApiAccess
	
	class ApiAccess (private val apiAuthToken:Option[String]){
		def lookup(symbol:String, code:String, requestContext:RequestContext = RequestContext()) = {
		  var request = Http("http://www.quandl.com/api/v1/datasets/DMDRN/" + symbol + "_"+code+".csv")
				  			.param("exclude_headers", "TRUE")
				  			.param("trim_start",requestContext.startDate.getOrElse("1990-01-01"))
				  			.param("trim_end",requestContext.endDate.getOrElse("2999-01-01"))
				  			.param("collapse",requestContext.frequency.getOrElse("NONE"))
				  			
		  if (!apiAuthToken.isEmpty) request = request.param("auth_token", apiAuthToken.get)
				  			
		  request.asString.split("\n") 
		  	.map (_ split ",") 
		  	.map ( a => a 
		  	    match {
		  	  		case x:Array[String] => (parseDate(x(0)), x(1).toDouble)
		  	  		case _ => throw new Exception("Expecting array of strings, got "+a)
		  		})
		}
		
		def parseDate(x:String) = {
		  x.replace("-", "").toInt
		}
	}
}