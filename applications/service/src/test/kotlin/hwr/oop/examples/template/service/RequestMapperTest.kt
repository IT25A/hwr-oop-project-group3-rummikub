package hwr.oop.examples.template.service

import hwr.oop.examples.template.service.model.*
import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.core.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class RequestMapperTest {

    @Test
    fun `StartGameRequest maps to command`() {
        // Arrange
        val request = StartGameRequest(
             listOf("player1", "player2")
        )

        // Act
        val command = with(RequestMapper) {
            request.asCommand()
        }

        // Assert
        assertNotNull(command.spielId)
        assertEquals(
            listOf("player1", "player2"),
            command.spielerIds
        )
    }

    @Test
    fun `DrawTileRequest maps to command`() {
        // Arrange
        val request = DrawTileRequest(
            "player1"
        )

        // Act
        val command = with(RequestMapper) {
            request.asCommand()
        }

        // Assert
        assertNotNull(command.spielId)
        assertEquals(
            SpielerId("player1"),
            command.spieler.id
        )
    }


    @Test
    fun `PlayTilesRequest maps to command`() {
        // Arrange
        val request = PlayTilesRequest(
            "player1",
            mutableListOf()
        )

        // Act
        val command = with(RequestMapper) {
            request.asCommand("game123")
        }

        // Assert
        assertEquals(
            SpielId("game123"),
            command.gameId
        )

        assertEquals(
            SpielerId("player1"),
            command.player.id
        )

        assertNotNull(command.table)
    }


    @Test
    fun `farbeUmwandeln converts RED`() {
        assertEquals(
            Farbe.Rot,
            RequestMapper.farbeUmwandeln("RED")
        )
    }


    @Test
    fun `farbeUmwandeln converts BLUE`() {
        assertEquals(
            Farbe.Blau,
            RequestMapper.farbeUmwandeln("BLUE")
        )
    }


    @Test
    fun `farbeUmwandeln converts YELLOW`() {
        assertEquals(
            Farbe.Orange,
            RequestMapper.farbeUmwandeln("YELLOW")
        )
    }


    @Test
    fun `farbeUmwandeln converts BLACK`() {
        assertEquals(
            Farbe.Schwarz,
            RequestMapper.farbeUmwandeln("BLACK")
        )
    }


    @Test
    fun `farbeUmwandeln throws exception for unknown color`() {
        assertThrows<IllegalArgumentException> {
            RequestMapper.farbeUmwandeln("GREEN")
        }
    }


    @Test
    fun `kombisUmwandeln converts RUN tiles to Folge`() {
        // Arrange
        val tileSet = TileSet(
            "RUN",
            emptyList()
        )

        // Act
        val table = RequestMapper.kombisUmwandeln(
            mutableListOf(tileSet)
        )

        // Assert
        assertEquals(1, table.tischReadOnly.size)
        assertTrue(table.tischReadOnly[0] is Folge)
    }


    @Test
    fun `kombisUmwandeln converts GROUP tiles to Sets`() {
        // Arrange
        val tileSet = TileSet(
             "GROUP",
             emptyList()
        )

        // Act
        val table = RequestMapper.kombisUmwandeln(
            mutableListOf(tileSet)
        )

        // Assert
        assertEquals(1, table.tischReadOnly.size)
        assertTrue(table.tischReadOnly[0] is Sets)
    }


    @Test
    fun `kombisUmwandeln ignores unknown set type`() {
        // Arrange
        val tileSet = TileSet(
            "UNKNOWN",
            emptyList()
        )

        // Act
        val table = RequestMapper.kombisUmwandeln(
            mutableListOf(tileSet)
        )

        // Assert
        assertTrue(table.tischReadOnly.isEmpty())
    }
}