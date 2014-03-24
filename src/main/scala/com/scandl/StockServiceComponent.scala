package com.scandl

import com.scandl.language.DataAccessRequest
import com.scandl.language.TimeSeries

trait StockServiceComponent {
	this:ApiAccessComponent =>
	val stockService: StockService
	
	class StockService (val metrics:Map[String,Attribute]) {
		def getMetric(symbols:List[String], metric:String) = {
		  symbols map {symbol => (symbol, Map(metric -> apiAccess.lookup(metrics.get(metric).get.getCode(symbol))))} toMap
		}
		
		def getMetrics(symbols:List[String], allMetrics:List[String]) = {
		  symbols map {s => (s, allMetrics map {m => (m, apiAccess.lookup(metrics.get(m).get.getCode(s)))} toMap )} toMap
		}
		
		def resolve(request:DataAccessRequest) = {
		  val data = (this getMetric(List(request.stock.name), request.metric.name))
		  	.get(request.stock.name)
		  	.get(request.metric.name)
		  
		  val dates = data map { _._1 }
		  val values = data map { _._2 }
		  
		  new TimeSeries(request.stock.name+"."+request.metric.name).withDatesAndValues(dates, values)
		}
	}
}