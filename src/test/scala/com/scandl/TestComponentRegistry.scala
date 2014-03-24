package com.scandl

import org.scalatest.mock.MockitoSugar

trait TestComponentRegistry extends StockServiceComponent 
									with ApiAccessComponent 
									with MockitoSugar{
	val stockService = new StockService(Map())
	val apiAccess = mock[ApiAccess]
}