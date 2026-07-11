package hwr.oop.rummikub_2026.applications

import hwr.oop.rummikub_2026.adapters.`in`.LoadGameByIdQuery
import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LoadGameByIdQueryTest {

    @Test
    fun `loadGameById converts string to SpielId and returns loaded game`() {
        // Arrange
        val spieler = Spieler(
            name = "Spieler1",
            id = SpielerId("p1"),
            brett = mutableListOf(),
            validateInitialCount= false
        )

        val expectedGame = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = Tisch(mutableListOf()),
            spieler = listOf(spieler),
            id = SpielId("game123")
        )

        val loadPort = FakeLoadGameByIdPort(expectedGame)

        val query = LoadGameByIdQuery(loadPort)

        // Act
        val result = query.loadGameById("game123")

        // Assert
        assertEquals(expectedGame, result)
        assertEquals(SpielId("game123"), loadPort.receivedId)
    }


    private class FakeLoadGameByIdPort(
        private val game: Spiel
    ) : LoadGameByIdPort {

        var receivedId: SpielId? = null

        override fun loadByid(gameId: SpielId): Spiel {
            receivedId = gameId
            return game
        }
    }
}