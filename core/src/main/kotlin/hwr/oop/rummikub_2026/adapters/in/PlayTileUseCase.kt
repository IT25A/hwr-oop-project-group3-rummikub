package hwr.oop.examples.doppelkopf_2026.adapters.`in`

import hwr.oop.examples.doppelkopf_2026.core.GameId
import hwr.oop.examples.doppelkopf_2026.ports.out.LoadGameByIdPort
import hwr.oop.examples.doppelkopf_2026.ports.out.SaveGamePort

class PlayTileUseCase(
	private val loadGameByIdPort: LoadGameByIdPort,
	private val saveGamePort: SaveGamePort,
) {
	
	fun playAction(command: Command) {
		val gameId = GameId(command.gameId)
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