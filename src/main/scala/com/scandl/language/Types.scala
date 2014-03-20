package com.scandl.language

import com.scandl.ComponentRegistry
import breeze.linalg.DenseVector

case class Metric(name:String)
case class Stock(name:String)

trait DeferredValueExpression {
  def value():DeferredValue
}

trait DeferredValue {
}

class TimeSeries(val name:String,dates:DenseVector[Int] = DenseVector(), values:DenseVector[Double] = DenseVector()) extends DeferredValue{
 
  if (dates.activeSize != values.activeSize) throw new Exception("Date and Values vectors are not cardinally aligned")
  
  def withDatesAndValues(dates:DenseVector[Int], values:DenseVector[Double]) = new TimeSeries(name, dates, values)
  def withDatesAndValues(dates:Array[Int], values:Array[Double]) = new TimeSeries(name, new DenseVector(dates), new DenseVector(values))
  
  override
  def toString() = {
    "Name: " + this.name + "\n\tDates: "+dates.toString()+"\n\tValues: "+values.toString()
  }
}

class TimeSeriesCollection(timeSeries:Array[TimeSeries]) extends DeferredValue {
  override
  def toString() = {
    val allStrings = timeSeries map { _.toString()} 
    allStrings.foldLeft("")((b,a) => b+a+"\n")
  }
}

class DataAccessRequest(val stock:Stock, val metric:Metric) extends DeferredValueExpression {
	def value() = ComponentRegistry.stockService.resolve(this)
}

class MultiDataAccessRequest(stocks:Array[Stock], metrics:Array[Metric]) extends DeferredValueExpression {
  def value() = {
	  val requests = for(stock <- stocks; metric<-metrics) yield new DataAccessRequest(stock,metric)
	  new TimeSeriesCollection(requests map ComponentRegistry.stockService.resolve)
  }
}
