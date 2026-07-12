package hwr.oop.rummikub_2026.ports.out

import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.SpielId

interface LoadGameByIdPort {
	fun loadByid(gameId: SpielId): Spiel
	
	class CouldNotLoadException(
      gameId: SpielId,
      cause: Exception? = null,
  ) : RuntimeException(
		"Could not load game with id: $gameId",
		cause
	)
}