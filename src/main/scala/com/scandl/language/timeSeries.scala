package com.scandl.language

import breeze.linalg.DenseVector
import scala.Array.canBuildFrom

class TimeSeries(val name:String, dates:DenseVector[Int] = DenseVector(), values:DenseVector[Double] = DenseVector()) extends DeferredValue{
 
  if (dates.activeSize != values.activeSize) throw new Exception("Date and Values vectors are not cardinally aligned")
  
  def withDatesAndValues(dates:DenseVector[Int], values:DenseVector[Double]) = new TimeSeries(name, dates, values)
  def withDatesAndValues(dates:Array[Int], values:Array[Double]) = new TimeSeries(name, new DenseVector(dates), new DenseVector(values))

  
  
  override
  def values(position:Int = 0):DenseVector[Double] = values
  
  override
  def dates(position:Int = 0):DenseVector[Int] = dates
  
  override
  def dataSeries():Map[String, Array[(Int, Double)]] = {
    Map{this.name -> dates.toArray.zip(values.toArray)}
  }
  
  override
  def toString() = {
    "Name: " + this.name + "\n\tDates: "+dates.toString()+"\n\tValues: "+values.toString()
  }
}

class TimeSeriesCollection(timeSeries:Array[TimeSeries]) extends DeferredValue {
  
  override
  def values(position:Int = 0):DenseVector[Double] = timeSeries(position).values()
  
  override
  def dates(position:Int = 0):DenseVector[Int] = timeSeries(position).dates()
  
  override
  def dataSeries():Map[String, Array[(Int, Double)]] = {
    timeSeries.foldLeft(Map[String, Array[(Int, Double)]]())((existing, series) => existing ++ series.dataSeries())
  }
  
  override
  def toString() = {
    val allStrings = timeSeries map { _.toString()} 
    allStrings.foldLeft("")((b,a) => b+a+"\n")
  }
}
