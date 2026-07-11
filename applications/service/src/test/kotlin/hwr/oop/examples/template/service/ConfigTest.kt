package hwr.oop.examples.template.service

import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.LoadGameByIdQuery
import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.ports.out.GameRepository
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ConfigTest {

    @Test
    fun `config creates new game use case`() {
        val config = Config()

        val savePort = FakeRepository()

        val useCase = config.newGameUseCase(savePort)

        assertNotNull(useCase)
    }


    @Test
    fun `config creates play tile use case`() {
        val config = Config()

        val repository = FakeRepository()

        val useCase = config.PlayTileUseCase(
            repository,
            repository
        )

        assertNotNull(useCase)
    }


    @Test
    fun `config creates draw tile use case`() {
        val config = Config()

        val repository = FakeRepository()

        val useCase = config.DrawTileUseCase(
            repository,
            repository
        )

        assertNotNull(useCase)
    }


    @Test
    fun `config creates load game query`() {
        val config = Config()

        val repository = FakeRepository()

        val query = config.loadGameByIdQuery(repository)

        assertNotNull(query)
    }


    private class FakeRepository : GameRepository {

        override fun save(spiel: hwr.oop.rummikub_2026.core.Spiel) {
        }

        override fun loadByid(
            gameId: hwr.oop.rummikub_2026.core.SpielId
        ): hwr.oop.rummikub_2026.core.Spiel {
            throw RuntimeException("not implemented")
        }
    }
}