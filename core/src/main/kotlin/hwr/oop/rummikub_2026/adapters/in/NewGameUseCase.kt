package hwr.oop.rummikub_2026.adapters.`in`

import hwr.oop.rummikub_2026.core.Spiel.Companion.erstelleZufaelligesSpiel
import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.core.SpielerId
import hwr.oop.rummikub_2026.ports.out.SpielSpeichernPort
import java.util.*

class NewGameUseCase(
	private val spielSpeichernPort: SpielSpeichernPort,
) {
	fun startGame(command: Command) {
		val spielerIds = command.spielerIds.map { playerId -> SpielerId(playerId)}
		val spielId = spielIdBasedOn(command)
		val withNine = command.withNine
		val spiel = erstelleZufaelligesSpiel(
			players = spielerIds,
			withNine = withNine,
			spielId = spielId
		)
		spielSpeichernPort.save(spiel)
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
		val withNine: Boolean = false,
	)
}