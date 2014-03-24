package com.scandl
import scalaj.http.Http
import scalaj.http.HttpOptions

case class RequestContext(startDate:Option[String] = None, endDate:Option[String] = None, frequency:Option[String] = None)

trait ApiAccessComponent {
	val apiAccess: ApiAccess
	
	val dataValidationPattern = "(\\d*-\\d*-\\d*),([\\d\\.]+),?.*"r
	
	class ApiAccess (private val apiAuthToken:Option[String]){
		def lookup(url:String, requestContext:RequestContext = RequestContext()) = {
		  var request = Http(url+".csv")
				  			.param("exclude_headers", "TRUE")
				  			.param("trim_start",requestContext.startDate.getOrElse("1990-01-01"))
				  			.param("trim_end",requestContext.endDate.getOrElse("2999-01-01"))
				  			.param("collapse",requestContext.frequency.getOrElse("NONE"))
				  			.option(HttpOptions.readTimeout(50000))
				  			
		  if (!apiAuthToken.isEmpty) request = request.param("auth_token", apiAuthToken.get)
				  			
		  request.asString.split("\n") 
		    .filter { dataValidationPattern.pattern.matcher(_).matches }
		  	.map ( a => a 
		  	    match {
		  	  		case dataValidationPattern(date,value) => (parseDate(date), value.toDouble)
		  	  		case _ => { throw new RuntimeException("Non validating pattern: "+a) }
		  		})
		}
		
		def parseDate(x:String) = {
		  x.replace("-", "").toInt
		}
	}
}