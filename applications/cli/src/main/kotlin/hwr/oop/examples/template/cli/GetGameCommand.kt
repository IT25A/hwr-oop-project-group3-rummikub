package hwr.oop.examples.template.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject

class GetGameCommand : CliktCommand(name = "getGame") {
	private val gameId by requireObject<String>()
	
	override fun run(): Unit = TODO()
}
