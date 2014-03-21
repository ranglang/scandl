package com.scandl.language

class StockInvocation(name:String) {
  val stock = Stock(name)
  
  def |(entity:StockInvocation) = Array(this, entity)
  def |(symbols:Seq[StockInvocation]) = this+:symbols
}
