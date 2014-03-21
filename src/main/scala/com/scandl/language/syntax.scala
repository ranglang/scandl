package com.scandl.language

import scalax.chart.Charting._
import org.jfree.data.xy.XYSeriesCollection

object Syntax {
	implicit def Symbol2Metic(name:Symbol) = new MetricInvocation(name.name)
	implicit def Symbol2Stock(name:Symbol) = new StockInvocation(name.name)
	implicit def Array2ParsedList[T](array:Array[T]) = new ParsedEntityList[T](array)

	def display(data:DeferredValueExpression) = println(data.value)
	def plot(data:DeferredValueExpression, title:String="Data Plot") = {
	  val series = data.value.dataSeries
	  val seriesCollection = new XYSeriesCollection()
	  for (seriesName <- series.keys) {
	    seriesCollection.addSeries(series(seriesName).toSeq.toXYSeries(seriesName))
	  }
	  val chart = XYLineChart(seriesCollection,title=title)
	  chart.show()
	}
}

class ParsedEntityList[T](array:Array[T]) {
  def |(entity:T):Seq[T] = this.array :+ entity 
}