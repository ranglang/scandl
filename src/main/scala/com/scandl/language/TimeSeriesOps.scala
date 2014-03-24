package com.scandl.language

import breeze.linalg.DenseVector

trait TimeSeriesOps {
  
  def ma(series:TimeSeries, window:Int):TimeSeries  = {
    val newValues = List.fill(window-1)(0.0) ::: (series.values().valuesIterator sliding window map (_.sum) map (_ / window) toList)
    series.withDatesAndValues(series.dates(), new DenseVector(newValues.toArray))
  }
  
  def ema(series:TimeSeries, window:Int, factor:Double):TimeSeries = {
    val newValues = List.fill(window-1)(0.0) ::: series.values().valuesIterator.scanLeft(0.0)((prev, curr) => {(curr - prev) * 2.0 / (window + 1) + prev}).toList
    series.withDatesAndValues(series.dates(), new DenseVector(newValues.toArray))
  }

}