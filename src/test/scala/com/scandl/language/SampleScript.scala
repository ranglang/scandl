package com.scandl.language

import com.scandl.language.Syntax._

object SampleScript {
	def main(args:Array[String]):Unit = {
	  
	  val GOOG_PE = 'PE_CURR of 'GOOG
	  plot(GOOG_PE,"Google PE")
	  
	  val assortedStocks = 'PE_CURR of 'GOOG|'AAPL|'TWTR
	  plot(assortedStocks, "Popular Tech Stock PE")
	
	}
}