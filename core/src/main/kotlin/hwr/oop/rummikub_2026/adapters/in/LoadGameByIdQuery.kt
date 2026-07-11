package hwr.oop.rummikub_2026.adapters.`in`

import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort

class LoadGameByIdQuery(
	private val loadGameByIdPort: LoadGameByIdPort,
) {
	
	fun loadGameById(gameId: String): Spiel {
		val gameId = SpielId(gameId)
		return loadGameByIdPort.loadByid(gameId)
	}
}