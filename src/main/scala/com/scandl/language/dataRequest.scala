package com.scandl.language

import com.scandl.ComponentRegistry
import com.scandl.StockServiceComponent

class DataAccessRequest(val stock:Stock, val metric:Metric, componentRegistry:StockServiceComponent = ComponentRegistry) extends DeferredValueExpression {
	def value() = componentRegistry.stockService.resolve(this)
}

class MultiDataAccessRequest(stocks:Seq[Stock], metrics:Seq[Metric], componentRegistry:StockServiceComponent = ComponentRegistry) extends DeferredValueExpression {
  def value() = {
	  val requests = for(stock <- stocks; metric<-metrics) yield new DataAccessRequest(stock,metric)
	  new TimeSeriesCollection(requests.toArray[DataAccessRequest] map componentRegistry.stockService.resolve)
  }
}