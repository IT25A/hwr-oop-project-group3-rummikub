package hwr.oop.rummikub_2026.applications

import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayTileUseCaseTest {
	@Test
	fun `playAction loads game plays tiles and saves updated game`() {
		val player = Spieler(
			name = "Player1",
			id = SpielerId("p1"),
			brett = mutableListOf(
				Stein(Farbe.Rot, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Eins)
			),
			validateInitialCount = false
		)
		
		val gameId = SpielId("game1")
		
		val game = Spiel(
			aktivSpieler = player,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			spieler = listOf(player),
			id = gameId
		)
		
		val loadPort = FakeLoadGamePort(game)
		val savePort = FakeSaveGamePort()
		
		val useCase = PlayTileUseCase(
			loadGameByIdPort = loadPort,
			saveGamePort = savePort
		)
		
		val command = PlayTileUseCase.Command(
			gameId = gameId,
			player = player,
			table = Tisch(mutableListOf())
		)
		
		useCase.playAction(command)
		
		assertEquals(gameId, loadPort.receivedId)
		
		assertNotNull(savePort.savedGame)
		
		assertTrue(
			savePort.savedGame!!
				.aktivSpieler
				.brettReadOnly
				.isEmpty()
		)
	}
	
	private class FakeLoadGamePort(
		private val game: Spiel,
	) : LoadGameByIdPort {
		
		var receivedId: SpielId? = null
		
		override fun loadByid(gameId: SpielId): Spiel {
			receivedId = gameId
			return game
		}
	}
	
	private class FakeSaveGamePort : SaveGamePort {
		var savedGame: Spiel? = null
		
		override fun save(spiel: Spiel) {
			savedGame = spiel
		}
	}
}