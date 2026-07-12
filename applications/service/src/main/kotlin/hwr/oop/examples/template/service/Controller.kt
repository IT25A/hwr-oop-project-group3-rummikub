package hwr.oop.examples.template.service


import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.LoadGameByIdQuery
import hwr.oop.examples.template.service.api.GameActionApi
import hwr.oop.examples.template.service.api.GameApi
import hwr.oop.examples.template.service.model.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val newGameUseCase: NewGameUseCase,
    private val playTileUseCase: PlayTileUseCase,
    private val drawTileUseCase: DrawTileUseCase,
    private val loadGameByIdQuery: LoadGameByIdQuery
) : GameApi, GameActionApi {

    override fun getGame(gameId: String?): ResponseEntity<GameState> {
        require(gameId != null) { "Game ID is null" }
        val loadedGame = loadGameByIdQuery.loadGameById(gameId)
        val response = with(ResponseMapper) {
            loadedGame.asGameResponse()
        }
        return ResponseEntity.ok(response)
    }

    override fun startGame(startGameRequest: @Valid StartGameRequest?): ResponseEntity<GameCreatedResponse> {
        require(startGameRequest != null) { "required request body (StartGameRequest) was null" }
        val command = with(RequestMapper) {
            startGameRequest.asCommand()
        }
        newGameUseCase.startGame(command)
        val response = GameCreatedResponse(command.spielId)
        return ResponseEntity.status(201).body(response)
    }

    override fun drawTile(
        gameId: String?,
        drawTileRequest: @Valid DrawTileRequest?,
    ): ResponseEntity<GameState>? {
        require(gameId != null) { "Game ID is null" }
        require(drawTileRequest != null) { "required request body (DrawTileRequest) was null" }
        val command: DrawTileUseCase.Command = with(RequestMapper) {
            drawTileRequest.asCommand()
        }
        drawTileUseCase.drawAction(command)
        return getGame(gameId)
    }

    override fun playTiles(
        gameId: String?,
        playTilesRequest: @Valid PlayTilesRequest?,
    ): ResponseEntity<GameState>? {
        require(gameId != null) { "Game ID is null" }
        require(playTilesRequest != null) { "required request body (PlayTileRequest) was null" }
        val command: PlayTileUseCase.Command = with(RequestMapper) {
            playTilesRequest.asCommand(gameId)
        }
        playTileUseCase.playAction(command)
        return getGame(gameId)
    }
}
