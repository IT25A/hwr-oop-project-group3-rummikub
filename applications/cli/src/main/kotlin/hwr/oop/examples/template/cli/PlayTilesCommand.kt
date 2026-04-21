package hwr.oop.examples.template.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class PlayTilesCommand : CliktCommand(name = "playTiles") {
	private val gameId by requireObject<String>()
	private val playerId by option("--player-id", help = "The ID of the player making the play.").required()
	private val tileSets by option(
		"--tile-set",
		help = "A tile set on the table after the turn, encoded as a string (e.g. define your own format). Pass once per set."
	).multiple(required = true)
	
	override fun run(): Unit = TODO()
}
