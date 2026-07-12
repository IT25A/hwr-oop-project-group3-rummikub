package hwr.oop.examples.template.service

import hwr.oop.examples.template.service.model.DrawTileRequest
import hwr.oop.examples.template.service.model.PlayTilesRequest
import hwr.oop.examples.template.service.model.StartGameRequest
import hwr.oop.examples.template.service.model.TileSet
import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.core.*
import java.util.*

object RequestMapper {
	fun StartGameRequest.asCommand() = NewGameUseCase.Command(
		spielId = UUID.randomUUID().toString(),
		spielerIds = this.playerIds
	)
	
	fun DrawTileRequest.asCommand() = DrawTileUseCase.Command(
		spielId = SpielId(UUID.randomUUID().toString()),
		spieler = Spieler(id = SpielerId(this.playerId), brett = mutableListOf(), validateInitialCount = false)
	)
	
	fun PlayTilesRequest.asCommand(gameId: String) = PlayTileUseCase.Command(
		gameId = SpielId(gameId),
		player = Spieler(id = SpielerId(this.playerId), brett = mutableListOf(), validateInitialCount = false),
		table = kombisUmwandeln(this.table)
	)
	
	fun kombisUmwandeln(komischerTisch: MutableList<TileSet>): Tisch {
		var umgewandelteKombis: MutableList<Kombinationen> = mutableListOf()
		
		for (kombi in komischerTisch) {
			var echteSteine: MutableList<Stein> = mutableListOf()
			
			for (dtoStein in kombi.tiles) {
				var echterStein =
					Stein(farbeUmwandeln(dtoStein.color), Zahl.entries.first { it.value == dtoStein.number.get() })
				echteSteine.add(echterStein)
			}
			
			if (kombi.setType == "RUN") {
				umgewandelteKombis.add(Folge(echteSteine))
			} else if (kombi.setType == "GROUP") {
				umgewandelteKombis.add(Sets(echteSteine))
			}
		}
		return Tisch(umgewandelteKombis)
	}
	
	fun farbeUmwandeln(color: String): hwr.oop.rummikub_2026.core.Farbe {
		return when (color) {
			"RED" -> hwr.oop.rummikub_2026.core.Farbe.Rot
			"BLUE" -> hwr.oop.rummikub_2026.core.Farbe.Blau
			"YELLOW" -> hwr.oop.rummikub_2026.core.Farbe.Orange
			"BLACK" -> hwr.oop.rummikub_2026.core.Farbe.Schwarz
			//"JOKER" -> hwr.oop.rummikub_2026.core.Farbe.JOKER
			else -> throw IllegalArgumentException("Unknown color: $color")
		}
	}
}