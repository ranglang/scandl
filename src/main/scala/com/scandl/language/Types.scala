package com.scandl.language

import breeze.linalg.DenseVector

case class Metric(name:String)
case class Stock(name:String) 

trait DeferredValueExpression {
  def value():DeferredValue
}

trait DeferredValue {
  def dataSeries():Map[String, Array[(Int, Double)]]
  def values(position:Int=0):DenseVector[Double]
  def dates(position:Int = 0):DenseVector[Int]
}