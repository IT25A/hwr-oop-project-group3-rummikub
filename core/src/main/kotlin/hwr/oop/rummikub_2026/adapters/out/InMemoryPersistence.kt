package hwr.oop.rummikub_2026.adapters.out

import hwr.oop.rummikub_2026.core.Game
import hwr.oop.rummikub_2026.core.GameId
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort

internal class InMemoryPersistence : LoadGameByIdPort, SaveGamePort {
	
	private val map = mutableMapOf<GameId, Game>()
	
	override fun save(game: Game) {
		val id = game.id()
		map[id] = game
	}
	
	override fun loadByid(gameId: GameId): Game =
		map[gameId] ?: throw LoadGameByIdPort.CouldNotLoadException(gameId)
}