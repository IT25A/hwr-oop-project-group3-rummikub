package hwr.oop.rummikub_2026.adapters.out

import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort

class InMemoryPersistence : LoadGameByIdPort, SaveGamePort {
	
	private val map = mutableMapOf<SpielId, Spiel>()
	
	override fun save(spiel: Spiel) {
		val id = spiel.id
		map[id] = spiel
	}
	
	override fun loadByid(spielId: SpielId): Spiel =
		map[spielId] ?: throw LoadGameByIdPort.CouldNotLoadException(spielId)
}