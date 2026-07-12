package hwr.oop.rummikub_2026.adapters.`in`

import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.core.SpielManager.erstelleZufaelligesSpiel
import hwr.oop.rummikub_2026.core.SpielerId
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import java.util.*

class NewGameUseCase(
	private val saveGamePort: SaveGamePort,
) {
	fun startGame(command: Command) {
		val spielerIds = command.spielerIds.map { playerId -> SpielerId(playerId) }
		val spielId = spielIdBasedOn(command)
		val spiel = erstelleZufaelligesSpiel(
			players = spielerIds,
			gameId = spielId
		)
		saveGamePort.save(spiel)
	}
	
	private fun spielIdBasedOn(command: Command): SpielId {
		val nullablespielId = command.spielId
		val spielId = if (nullablespielId != null) {
			val uuid = UUID.fromString(nullablespielId)
			SpielId.from(uuid)
		} else {
			SpielId.random()
		}
		return spielId
	}
	
	data class Command(
		val spielId: String? = null,
		val spielerIds: List<String>,
	)
}