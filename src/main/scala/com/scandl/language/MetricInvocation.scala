package com.scandl.language

class MetricInvocation(name:String) {
  val metric = Metric(name)
  
  def of(symbol:StockInvocation): DeferredValueExpression = {
	 new DataAccessRequest(symbol.stock, this.metric)
  }
  
  def of(symbol:Symbol): DeferredValueExpression = {
    this.of(new StockInvocation(symbol.name))
  }
  
  def of(symbols:StockList): DeferredValueExpression = {
    new MultiDataAccessRequest(symbols.toArray map {_.stock}, Array[Metric](this.metric))
  }

}
