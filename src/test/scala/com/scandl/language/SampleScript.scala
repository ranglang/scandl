package com.scandl.language

import com.scandl.language.LanguageImplicits._

object SampleScript {
	def main(args:Array[String]):Unit = {
	  val GOOG_PE = 'PE_CURR of 'GOOG
	  display(GOOG_PE)
	  
	  val assortedStocks = 'PE_CURR of 'GOOG|'AAPL|'TWTR
	  display(assortedStocks)
	}
}