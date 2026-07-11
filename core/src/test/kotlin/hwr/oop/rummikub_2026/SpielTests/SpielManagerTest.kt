package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SpielManagerTest {

    @Test
    fun `erstelleZufaelligesSpiel creates game with correct players`() {
        // Arrange
        val players = listOf(
            SpielerId("p1"),
            SpielerId("p2")
        )
        val gameId = SpielId("testGame")

        // Act
        val spiel = SpielManager.erstelleZufaelligesSpiel(players, gameId)

        // Assert
        assertEquals(gameId, spiel.id)
        assertEquals(2, spiel.spieler.size)

        assertEquals(SpielerId("p1"), spiel.spieler[0].id)
        assertEquals(SpielerId("p2"), spiel.spieler[1].id)

        assertEquals(spiel.spieler[0], spiel.aktivSpieler)
    }

    @Test
    fun `erstelleZufaelligesSpiel distributes 14 stones to every player`() {
        // Arrange
        val players = listOf(
            SpielerId("p1"),
            SpielerId("p2"),
            SpielerId("p3"),
            SpielerId("p4")
        )

        // Act
        val spiel = SpielManager.erstelleZufaelligesSpiel(players)

        // Assert
        assertEquals(4, spiel.spieler.size)

        spiel.spieler.forEach { spieler ->
            assertEquals(14, spieler.brettReadOnly.size)
        }
    }

    @Test
    fun `erstelleZufaelligesSpiel creates remaining bag`() {
        // Arrange
        val players = listOf(
            SpielerId("p1"),
            SpielerId("p2")
        )

        // Act
        val spiel = SpielManager.erstelleZufaelligesSpiel(players)

        // Assert
        // Rummikub has 106 stones, 2 players receive 28
        assertEquals(78, spiel.beutel.size)
    }

    @Test
    fun `erstelleZufaelligesSpiel rejects less than two players`() {
        // Arrange
        val players = listOf(
            SpielerId("p1")
        )

        // Act + Assert
        assertThrows<IllegalArgumentException> {
            SpielManager.erstelleZufaelligesSpiel(players)
        }
    }

    @Test
    fun `erstelleZufaelligesSpiel rejects more than four players`() {
        // Arrange
        val players = listOf(
            SpielerId("p1"),
            SpielerId("p2"),
            SpielerId("p3"),
            SpielerId("p4"),
            SpielerId("p5")
        )

        // Act + Assert
        assertThrows<IllegalArgumentException> {
            SpielManager.erstelleZufaelligesSpiel(players)
        }
    }

    @Test
    fun `erstelleZufaelligesSpiel works with minimum and maximum player count`() {
        // Arrange
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

        // Act
        val spiel2 = SpielManager.erstelleZufaelligesSpiel(twoPlayers)
        val spiel4 = SpielManager.erstelleZufaelligesSpiel(fourPlayers)

        // Assert
        assertEquals(2, spiel2.spieler.size)
        assertEquals(4, spiel4.spieler.size)
    }
    @Test
    fun `erstelleZufaelligesSpiel accepts valid player count`() {
        // Arrange
        val players = listOf(
            SpielerId("spieler1"),
            SpielerId("spieler2")
        )

        // Act
        val spiel = SpielManager.erstelleZufaelligesSpiel(players)

        // Assert
        assertNotNull(spiel)
        assertEquals(2, spiel.spieler.size)
    }
}