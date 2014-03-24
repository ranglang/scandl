package com.scandl

case class PriceAttribute(val description:String) extends Attribute {
  val name="PRICE"
  def getCode(symbol:String) = "http://www.quandl.com/api/v1/datasets/GOOG/NASDAQ_"+symbol
}

case class FundamentalsAttribute(val name:String, val description:String) extends Attribute {
  def getCode(symbol:String) = "http://www.quandl.com/api/v1/datasets/DMDRN/"+symbol+"_"+name
}