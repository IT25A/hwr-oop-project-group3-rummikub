package hwr.oop.examples.template.service

import hwr.oop.rummikub_2026.core.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ResponseMapperTest {
	@Test
	fun `asGameResponse maps game id`() {
		val spieler = Spieler(
			name = "Spieler1",
			id = SpielerId("player1"),
			brett = mutableListOf(),
			validateInitialCount = false
		)
		
		val game = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			spieler = listOf(spieler),
			id = SpielId("game123")
		)
		
		val response = with(ResponseMapper) {
			game.asGameResponse()
		}
		
		assertEquals(
			"game123",
			response.gameId
		)
	}
	
	@Test
	fun `asGameResponse works with random game id`() {
		val spielId = SpielId.random()
		
		val spieler = Spieler(
			name = "Spieler1",
			id = SpielerId("player1"),
			brett = mutableListOf(),
			validateInitialCount = false
		)
		
		val game = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			spieler = listOf(spieler),
			id = spielId
		)
		
		val response = with(ResponseMapper) {
			game.asGameResponse()
		}
		
		assertEquals(
			spielId.wert,
			response.gameId
		)
	}
}