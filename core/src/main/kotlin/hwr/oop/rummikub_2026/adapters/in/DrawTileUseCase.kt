package hwr.oop.rummikub_2026.adapters.`in`

import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.core.Spieler
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort

class DrawTileUseCase(
	private val loadGameByIdPort: LoadGameByIdPort,
	private val saveGamePort: SaveGamePort,
) {
	fun drawAction(command: Command) {
		val gameId = SpielId(command.spielId.wert)
		val loadedGame = loadGameByIdPort.loadByid(gameId)
		val updatedGame = loadedGame.ziehen(command.spieler)
		saveGamePort.save(updatedGame)
	}
	
	data class Command(
		val spielId: SpielId,
		val spieler: Spieler,
	)
}