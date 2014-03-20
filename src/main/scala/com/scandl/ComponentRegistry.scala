package com.scandl

import com.typesafe.config.ConfigFactory
import java.net.URLClassLoader

object ComponentRegistry extends ApiAccessComponent with StockServiceComponent {
 val conf = ConfigFactory.load 
 lazy val quandlAccessToken = if (conf.hasPath("quandl.accesstoken")) Some(conf.getString("quandl.accesstoken")) else None
  
 val apiAccess = new ApiAccess(quandlAccessToken)
 val stockService = new StockService
}