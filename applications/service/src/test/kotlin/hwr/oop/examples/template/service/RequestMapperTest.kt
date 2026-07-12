package hwr.oop.examples.template.service

import hwr.oop.examples.template.service.model.DrawTileRequest
import hwr.oop.examples.template.service.model.PlayTilesRequest
import hwr.oop.examples.template.service.model.StartGameRequest
import hwr.oop.examples.template.service.model.TileSet
import hwr.oop.rummikub_2026.core.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RequestMapperTest {
	@Test
	fun `StartGameRequest maps to command`() {
		val request = StartGameRequest(
			listOf("player1", "player2")
		)
		
		val command = with(RequestMapper) {
			request.asCommand()
		}
		
		assertNotNull(command.spielId)
		assertEquals(
			listOf("player1", "player2"),
			command.spielerIds
		)
	}
	
	@Test
	fun `DrawTileRequest maps to command`() {
		val request = DrawTileRequest(
			"player1"
		)
		
		val command = with(RequestMapper) {
			request.asCommand()
		}
		
		assertNotNull(command.spielId)
		assertEquals(
			SpielerId("player1"),
			command.spieler.id
		)
	}
	
	@Test
	fun `PlayTilesRequest maps to command`() {
		val request = PlayTilesRequest(
			"player1",
			mutableListOf()
		)
		
		val command = with(RequestMapper) {
			request.asCommand("game123")
		}
		
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
		val tileSet = TileSet(
			"RUN",
			emptyList()
		)
		
		val table = RequestMapper.kombisUmwandeln(
			mutableListOf(tileSet)
		)
		
		assertEquals(1, table.tischReadOnly.size)
		assertTrue(table.tischReadOnly[0] is Folge)
	}
	
	@Test
	fun `kombisUmwandeln converts GROUP tiles to Sets`() {
		val tileSet = TileSet(
			"GROUP",
			emptyList()
		)
		
		val table = RequestMapper.kombisUmwandeln(
			mutableListOf(tileSet)
		)
		
		assertEquals(1, table.tischReadOnly.size)
		assertTrue(table.tischReadOnly[0] is Sets)
	}
	
	@Test
	fun `kombisUmwandeln ignores unknown set type`() {
		val tileSet = TileSet(
			"UNKNOWN",
			emptyList()
		)
		
		val table = RequestMapper.kombisUmwandeln(
			mutableListOf(tileSet)
		)
		
		assertTrue(table.tischReadOnly.isEmpty())
	}
}