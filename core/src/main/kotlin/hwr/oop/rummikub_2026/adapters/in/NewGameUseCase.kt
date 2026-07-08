package hwr.oop.examples.doppelkopf_2026.adapters.`in`

import hwr.oop.examples.doppelkopf_2026.core.Game.Companion.createRandomGame
import hwr.oop.examples.doppelkopf_2026.core.GameId
import hwr.oop.examples.doppelkopf_2026.core.PlayerId
import hwr.oop.examples.doppelkopf_2026.ports.out.SaveGamePort
import java.util.*

class NewGameUseCase(
	private val saveGamePort: SaveGamePort,
) {
	fun startGame(command: Command) {
		val playerIds = command.playersIds.map { playerId -> PlayerId(playerId) }
		val gameId = gameIdBasedOn(command)
		val withNine = command.withNine
		val game = createRandomGame(
			players = playerIds,
			withNine = withNine,
			gameId = gameId
		)
		saveGamePort.save(game)
	}
	
	private fun gameIdBasedOn(command: Command): GameId {
		val nullableGameId = command.gameId
		val gameId = if (nullableGameId != null) {
			val uuid = UUID.fromString(nullableGameId)
			GameId.from(uuid)
		} else {
			GameId.random()
		}
		return gameId
	}
	
	data class Command(
		val gameId: String? = null,
		val playersIds: List<String>,
		val withNine: Boolean = false,
	)
}