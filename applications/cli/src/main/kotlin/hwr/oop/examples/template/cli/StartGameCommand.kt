package hwr.oop.examples.template.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option

<<<<<<< HEAD
class 	StartGameCommand : CliktCommand(name = "startGame") {
=======
class StartGameCommand : CliktCommand(name = "startGame") {
>>>>>>> 9033c16f36e49b84ff19ef7be9e8abf30a4d8e90
	private val playerIds by option(
		"--player-id",
		help = "ID of a player joining the game. Pass multiple times for each player (2–4 total)."
	).multiple(required = true)
	
	override fun run(): Unit = TODO()
}
