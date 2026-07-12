package hwr.oop.rummikub_2026.adapters.`in`

import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort

class PlayTileUseCase(
	private val loadGameByIdPort: LoadGameByIdPort,
	private val saveGamePort: SaveGamePort,
) {
	fun playAction(command: Command) {
		val gameId = SpielId(command.gameId.wert)
		val loadedGame = loadGameByIdPort.loadByid(gameId)
		val updatedGame = loadedGame.auslegen(
			command.player,
			true,
			listOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Schwarz, Zahl.Eins), Stein(Farbe.Blau, Zahl.Eins))
		)
		saveGamePort.save(updatedGame)
	}
	
	data class Command(
		val gameId: SpielId,
		val player: Spieler,
		val table: Tisch,
	)
}