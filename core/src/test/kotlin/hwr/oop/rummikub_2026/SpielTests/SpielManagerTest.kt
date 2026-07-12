package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SpielManagerTest {
	@Test
	fun `erstelleZufaelligesSpiel creates game with correct players`() {
		val players = listOf(
			SpielerId("p1"),
			SpielerId("p2")
		)
		
		val gameId = SpielId("testGame")
		
		val spiel = SpielManager.erstelleZufaelligesSpiel(players, gameId)
		
		assertEquals(gameId, spiel.id)
		assertEquals(2, spiel.spieler.size)
		
		assertEquals(SpielerId("p1"), spiel.spieler[0].id)
		assertEquals(SpielerId("p2"), spiel.spieler[1].id)
		
		assertEquals(spiel.spieler[0], spiel.aktivSpieler)
	}
	
	@Test
	fun `erstelleZufaelligesSpiel distributes 14 stones to every player`() {
		val players = listOf(
			SpielerId("p1"),
			SpielerId("p2"),
			SpielerId("p3"),
			SpielerId("p4")
		)
		
		val spiel = SpielManager.erstelleZufaelligesSpiel(players)
		
		assertEquals(4, spiel.spieler.size)
		
		spiel.spieler.forEach { spieler ->
			assertEquals(14, spieler.brettReadOnly.size)
		}
	}
	
	@Test
	fun `erstelleZufaelligesSpiel creates remaining bag`() {
		val players = listOf(
			SpielerId("p1"),
			SpielerId("p2")
		)
		
		val spiel = SpielManager.erstelleZufaelligesSpiel(players)
		
		assertEquals(78, spiel.beutel.size)
	}
	
	@Test
	fun `erstelleZufaelligesSpiel rejects less than two players`() {
		val players = listOf(
			SpielerId("p1")
		)
		
		assertThrows<IllegalArgumentException> {
			SpielManager.erstelleZufaelligesSpiel(players)
		}
	}
	
	@Test
	fun `erstelleZufaelligesSpiel rejects more than four players`() {
		val players = listOf(
			SpielerId("p1"),
			SpielerId("p2"),
			SpielerId("p3"),
			SpielerId("p4"),
			SpielerId("p5")
		)
		
		assertThrows<IllegalArgumentException> {
			SpielManager.erstelleZufaelligesSpiel(players)
		}
	}
	
	@Test
	fun `erstelleZufaelligesSpiel works with minimum and maximum player count`() {
		val twoPlayers = listOf(
			SpielerId("p1"),
			SpielerId("p2")
		)
		
		val fourPlayers = listOf(
			SpielerId("p1"),
			SpielerId("p2"),
			SpielerId("p3"),
			SpielerId("p4")
		)
		
		val spiel2 = SpielManager.erstelleZufaelligesSpiel(twoPlayers)
		val spiel4 = SpielManager.erstelleZufaelligesSpiel(fourPlayers)
		
		assertEquals(2, spiel2.spieler.size)
		assertEquals(4, spiel4.spieler.size)
	}
	
	@Test
	fun `erstelleZufaelligesSpiel accepts valid player count`() {
		val players = listOf(
			SpielerId("spieler1"),
			SpielerId("spieler2")
		)
		
		val spiel = SpielManager.erstelleZufaelligesSpiel(players)
		
		assertNotNull(spiel)
		assertEquals(2, spiel.spieler.size)
	}
}