package hwr.oop.rummikub_2026.applications

import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.UUID

class NewGameUseCaseTest {

    @Test
    fun `startGame creates game with provided id and saves it`() {
        // Arrange
        val savePort = FakeSaveGamePort()
        val useCase = NewGameUseCase(savePort)

        val gameId = UUID.randomUUID().toString()

        val command = NewGameUseCase.Command(
            spielId = gameId,
            spielerIds = listOf("player1", "player2")
        )

        // Act
        useCase.startGame(command)

        // Assert
        val savedGame = savePort.savedGame

        assertNotNull(savedGame)
        assertEquals(SpielId(gameId), savedGame!!.id)

        assertEquals(2, savedGame.spieler.size)
        assertEquals(SpielerId("player1"), savedGame.spieler[0].id)
        assertEquals(SpielerId("player2"), savedGame.spieler[1].id)
    }


    @Test
    fun `startGame creates random id when no id is provided`() {
        // Arrange
        val savePort = FakeSaveGamePort()
        val useCase = NewGameUseCase(savePort)

        val command = NewGameUseCase.Command(
            spielerIds = listOf("player1", "player2")
        )

        // Act
        useCase.startGame(command)

        // Assert
        val savedGame = savePort.savedGame

        assertNotNull(savedGame)
        assertDoesNotThrow {
            UUID.fromString(savedGame!!.id.wert)
        }
    }


    @Test
    fun `startGame saves game`() {
        // Arrange
        val savePort = FakeSaveGamePort()
        val useCase = NewGameUseCase(savePort)

        val command = NewGameUseCase.Command(
            spielerIds = listOf("player1", "player2")
        )

        // Act
        useCase.startGame(command)

        // Assert
        assertNotNull(savePort.savedGame)
    }


    private class FakeSaveGamePort : SaveGamePort {

        var savedGame: Spiel? = null

        override fun save(spiel: Spiel) {
            savedGame = spiel
        }
    }
}