package com.scandl.language

import scalax.chart.Charting._
import org.jfree.data.xy.XYSeriesCollection
import com.scandl.StockServiceComponent
import com.scandl.ComponentRegistry
import scala.util.Sorting

object Syntax extends TimeSeriesOps{
	
	implicit def Symbol2Metic(name:Symbol) = new MetricInvocation(name.name)
	implicit def Symbol2Stock(name:Symbol) = new StockInvocation(name.name)
	implicit def Array2ParsedList[T](array:Array[T]) = new ParsedEntityList[T](array)
	implicit def DeferredValueExpression2TimeSeries(value:DeferredValueExpression) = value.value().asInstanceOf[TimeSeries]
	implicit def DeferredValue2TimeSeries(value:DeferredValue) = value.asInstanceOf[TimeSeries]
	implicit def DeferredValueExpression2DeferredValue(value:DeferredValueExpression) = value.value()

	def display(data:DeferredValue) = println(data)
	def plot(data:DeferredValue, title:String="Data Plot") = {
	  val series = data.dataSeries
	  val seriesCollection = new XYSeriesCollection()
	  for (seriesName <- series.keys) {
	    seriesCollection.addSeries(series(seriesName).toSeq.toXYSeries(seriesName))
	  }
	  val chart = XYLineChart(seriesCollection,title=title)
	  chart.show()
	}
	
	def metrics = _metrics()
	
	private def _metrics (componentRegistry:StockServiceComponent = ComponentRegistry) = {
	  val names = (componentRegistry.stockService.metrics.values map {_.toString} toList).sorted foreach {println(_)}
	}
}

class ParsedEntityList[T](array:Array[T]) {
  def |(entity:T):Seq[T] = this.array :+ entity 
}