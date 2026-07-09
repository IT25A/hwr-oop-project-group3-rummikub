package hwr.oop.rummikub_2026.adapters.`in`

import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.ports.out.SpielLadenByIdPort
import hwr.oop.rummikub_2026.ports.out.SpielSpeichernPort

class PlayTileUseCase(
	private val loadGameByIdPort: SpielLadenByIdPort,
	private val saveGamePort: SpielSpeichernPort,
) {
	
	fun playAction(command: Command) {
		val gameId = SpielId(command.gameId)
		val loadedGame = loadGameByIdPort.loadByid(gameId)
		val updatedGame = TODO("domain logic on game")
		saveGamePort.save(updatedGame)
	}
	
	data class Command(
		val gameId: String,
		val player: String,
		val suit: String,
		val rank: String,
	)
	
}