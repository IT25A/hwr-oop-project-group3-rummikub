package hwr.oop.examples.doppelkopf_2026.adapters.`in`

import hwr.oop.examples.doppelkopf_2026.core.Game
import hwr.oop.examples.doppelkopf_2026.core.GameId
import hwr.oop.examples.doppelkopf_2026.ports.out.LoadGameByIdPort

class LoadGameByIdQuery(
	private val loadGameByIdPort: LoadGameByIdPort,
) {
	
	fun loadGameById(gameId: String): Game {
		val gameId = GameId(gameId)
		return loadGameByIdPort.loadByid(gameId)
	}
}