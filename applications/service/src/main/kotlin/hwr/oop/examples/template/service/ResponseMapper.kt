package hwr.oop.examples.template.service

import org.openapitools.jackson.nullable.JsonNullable

object ResponseMapper {
	private typealias ApiGame = hwr.oop.examples.template.service.model.GameState
	private typealias ApiTile = hwr.oop.examples.template.service.model.Tile
	
	private typealias CoreGame = hwr.oop.rummikub_2026.core.Spiel
	private typealias CoreTile = hwr.oop.rummikub_2026.core.Stein
	
	fun CoreGame.asGameResponse(): ApiGame = ApiGame().also {
		it.gameId = this.id.wert
	}
	
	private fun List<CoreTile>.asApiTiles(): List<ApiTile> = this.map {
		it.asApiTile()
	}
	
	private fun CoreTile.asApiTile(): ApiTile = ApiTile().also {
		it.color = this.farbe().name
		it.number = JsonNullable.of(this.zahl().value)
	}
}