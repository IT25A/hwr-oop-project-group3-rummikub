package hwr.oop.rummikub_2026.applications

import hwr.oop.rummikub_2026.adapters.out.InMemoryPersistence
import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InMemoryPersistenceTest {
	private fun createSpiel(id: SpielId): Spiel {
		val spieler = Spieler(
			name = "Spieler1",
			id = SpielerId("p1"),
			brett = mutableListOf(),
			validateInitialCount = false
		)
		
		return Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			spieler = listOf(spieler),
			id = id
		)
	}
	
	@Test
	fun `save stores game and loadByid returns it`() {
		val persistence = InMemoryPersistence()
		val gameId = SpielId("game1")
		val spiel = createSpiel(gameId)
		
		persistence.save(spiel)
		val loadedGame = persistence.loadByid(gameId)
		
		assertEquals(spiel, loadedGame)
	}
	
	@Test
	fun `loadByid throws exception when game does not exist`() {
		val persistence = InMemoryPersistence()
		val gameId = SpielId("unknown")
		
		assertThrows<LoadGameByIdPort.CouldNotLoadException> {
			persistence.loadByid(gameId)
		}
	}
	
	@Test
	fun `saving another game with same id overwrites old game`() {
		val persistence = InMemoryPersistence()
		val gameId = SpielId("same-id")
		
		val firstGame = createSpiel(gameId)
		
		val secondPlayer = Spieler(
			name = "Spieler2",
			id = SpielerId("p2"),
			brett = mutableListOf(),
			validateInitialCount = false
		)
		
		val secondGame = Spiel(
			aktivSpieler = secondPlayer,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			spieler = listOf(secondPlayer),
			id = gameId
		)
		
		persistence.save(firstGame)
		persistence.save(secondGame)
		
		assertEquals(secondGame, persistence.loadByid(gameId))
	}
}