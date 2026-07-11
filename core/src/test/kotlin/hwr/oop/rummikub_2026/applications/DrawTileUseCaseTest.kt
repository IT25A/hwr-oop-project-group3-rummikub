package hwr.oop.rummikub_2026.applications

import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DrawTileUseCaseTest {

    @Test
    fun `drawAction loads game draws tile and saves updated game`() {
        // Arrange
        val spieler = Spieler(
            name = "Spieler1",
            id = SpielerId("p1"),
            brett = mutableListOf(),
            validateInitialCount= false
        )

        val gameId = SpielId("game1")

        val originalGame = Spiel(
            aktivSpieler = spieler,
            beutel = listOf(
                Stein(Farbe.Rot, Zahl.Eins)
            ),
            tisch = Tisch(mutableListOf()),
            spieler = listOf(spieler),
            id = gameId
        )

        val loadPort = FakeLoadGamePort(originalGame)
        val savePort = FakeSaveGamePort()

        val useCase = DrawTileUseCase(
            loadGameByIdPort = loadPort,
            saveGamePort = savePort
        )

        val command = DrawTileUseCase.Command(
            spielId = gameId,
            spieler = spieler
        )

        // Act
        useCase.drawAction(command)

        // Assert
        assertEquals(gameId, loadPort.loadedId)
        assertNotNull(savePort.savedGame)

        assertEquals(0, savePort.savedGame!!.beutel.size)
        assertEquals(1, savePort.savedGame!!.aktivSpieler.brettReadOnly.size)
    }


    private class FakeLoadGamePort(
        private val game: Spiel
    ) : LoadGameByIdPort {

        var loadedId: SpielId? = null

        override fun loadByid(gameId: SpielId): Spiel {
            loadedId = gameId
            return game
        }
    }


    private class FakeSaveGamePort : SaveGamePort {

        var savedGame: Spiel? = null

        override fun save(game: Spiel) {
            savedGame = game
        }
    }
}