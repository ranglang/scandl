package com.scandl.language

import breeze.linalg.DenseVector

case class Metric(name:String)
case class Stock(name:String) 

trait DeferredValueExpression {
  def value():DeferredValue
}

trait DeferredValue {
  def dataSeries():Map[String, Array[(Int, Double)]]
}


