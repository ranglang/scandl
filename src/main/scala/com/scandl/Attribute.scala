package com.scandl

trait Attribute {
	val name:String;
	def getCode(input:String):String;
	val description:String;
	
	override
	def toString():String = {
	  name+"\t\t"+description
	}
}