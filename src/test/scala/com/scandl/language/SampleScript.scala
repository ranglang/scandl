package com.scandl.language

import com.scandl.language.Syntax._

object SampleScript {
	def main(args:Array[String]):Unit = {
	  
	  metrics
	  
	  val GOOG_PE = 'PE_CURR of 'GOOG
	  plot(GOOG_PE,"Google PE")
	  
	  val assortedStocks = 'PRICE of 'GOOG|'AAPL
	  plot(assortedStocks, "Popular Tech Stock PE")
	}
}