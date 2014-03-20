package com.scandl.language

class StockInvocation(symbol:String){
	val stock = Stock(symbol)
	
	def |(symbol:StockInvocation) = new StockList(Array(this, symbol))
	
	def |(symbols:StockList) = new StockList(this+:symbols.toArray())
}

class StockList (list:Array[StockInvocation]){

  def |(symbol:StockInvocation) = new StockList(symbol+:list)
  
  def toArray() = {list}
  
}