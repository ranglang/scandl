package com.scandl.language

import com.scandl.ComponentRegistry

object LanguageImplicits {
	implicit def Symbol2Metic(name:Symbol) = new MetricInvocation(name.name)
	implicit def Symbol2Stock(name:Symbol) = new StockInvocation(name.name)

	def display(data:DeferredValueExpression) = println(data.value)
}