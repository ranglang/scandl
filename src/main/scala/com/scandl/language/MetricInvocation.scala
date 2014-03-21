package com.scandl.language

class MetricInvocation(name:String) {
  val metric = Metric(name)
  
  def of(symbol:StockInvocation): DeferredValueExpression = {
	 new DataAccessRequest(symbol.stock, this.metric)
  }
  
  def of(symbols:Seq[StockInvocation]): DeferredValueExpression = {
    new MultiDataAccessRequest(symbols.map {a => a.stock}, Array[Metric](this.metric))
  }
}
