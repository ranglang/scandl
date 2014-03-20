package com.scandl

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._;

class StockServiceSpec extends FlatSpec with Matchers with TestComponentRegistry with MockitoSugar {
  
  "A StockService" should "return a time series for each entity passed to getMetric()" in {
    
    when(this.apiAccess.lookup("ABC","Metric1")).thenReturn(Array((20140101,100.0),(20140102,200.0)))
    when(this.apiAccess.lookup("CBA","Metric1")).thenReturn(Array((20140101,100.0),(20140102,200.0)))
    
    val dataset = this.stockService.getMetric(List("ABC","CBA"), "Metric1")
    
    verify(this.apiAccess, times(1)).lookup("ABC", "Metric1")
    val expectedResult = Map("ABC" -> Map("Metric1" -> Array((20140101,100.0),(20140102,200.0))),
    						 "CBA" -> Map("Metric1" -> Array((20140101,100.0),(20140102,200.0))))
    assert(dataset.equals(expectedResult))
  }
  
  it should "return a time series for each metric passed to getMetrics()" in {
    when(this.apiAccess.lookup("ABC","Metric1")).thenReturn(Array((20140101,100.0),(20140102,200.0)))
    when(this.apiAccess.lookup("ABC","Metric2")).thenReturn(Array((20140101,100.0),(20140102,200.0)))
    
    val dataset = this.stockService.getMetrics(List("ABC"), List("Metric1", "Metric2"))
    
    val expectedResult = Map("ABC" -> Map("Metric1" -> Array((20140101,100),(20140102,200)), "Metric2" -> Array((20140101,100),(20140102,200))))
    assert(dataset.equals(expectedResult))
  }
  

}