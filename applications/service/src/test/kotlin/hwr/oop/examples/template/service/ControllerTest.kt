package hwr.oop.examples.template.service

import hwr.oop.examples.template.service.model.StartGameRequest
import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.LoadGameByIdQuery
import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.adapters.out.InMemoryPersistence
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class ControllerTest {

    private val persistence = InMemoryPersistence()

    private val newGameUseCase = NewGameUseCase(
        persistence
    )

    private val loadGameByIdQuery = LoadGameByIdQuery(
        persistence
    )

    private val drawTileUseCase = DrawTileUseCase(
        persistence,
        persistence
    )

    private val playTileUseCase = PlayTileUseCase(
        persistence,
        persistence
    )

    private val controller = Controller(
        newGameUseCase,
        playTileUseCase,
        drawTileUseCase,
        loadGameByIdQuery
    )

    @Test
    fun `startGame creates game and returns created`() {
        // Arrange
        val request = StartGameRequest(
            listOf(
                "player1",
                "player2"
            )
        )

        // Act
        val response = controller.startGame(request)

        // Assert
        assertEquals(
            201,
            response.statusCode.value()
        )

        assertNotNull(response.body)
    }


    @Test
    fun `startGame rejects null request`() {
        assertThrows<IllegalArgumentException> {
            controller.startGame(null)
        }
    }


    @Test
    fun `getGame rejects null id`() {
        assertThrows<IllegalArgumentException> {
            controller.getGame(null)
        }
    }


    @Test
    fun `getGame returns game after creation`() {
        // Arrange
        val startRequest = StartGameRequest(
            listOf(
                "player1",
                "player2"
            )
        )

        val createResponse =
            controller.startGame(startRequest)

        val gameId =
            createResponse.body!!.gameId

        // Act
        val response =
            controller.getGame(gameId)

        // Assert
        assertEquals(
            200,
            response.statusCode.value()
        )

        assertNotNull(response.body)
    }
}