# Scandl

[In Development] Scandl is a library that adds a simple data querying and time series manipulation environment for Quandl datasets.  For information on Quandl, see <a href="http://www.quandl.com/help/api-for-stock-data">Quandl's API documentation</a>

Scala + Quandl = Scandl


## Example Use
See the example script under src/test/scala/com/scandl/language/SampleScript:

```scala
import com.scandl.language.LanguageImplicits._

object SampleScript {
	def main(args:Array[String]):Unit = {
	  val GOOG_PE = 'PE_CURR of 'GOOG
	  display(GOOG_PE)
	  
	  val assortedStocks = 'PE_CURR of 'GOOG|'AAPL|'TWTR
	  display(assortedStocks)
	}
}
```

## Quandl Access Token

To use an established Quandl API access token, you need to have a file on your classpath that looks something like the following (this project uses TypeSafe configuration):

'''json
quandl {
    accesstoken="XXXXXXXXXXXX"
}
'''