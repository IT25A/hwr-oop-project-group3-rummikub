package hwr.oop.examples.template.service

import hwr.oop.examples.template.FileSystemPersistence
import hwr.oop.examples.template.FileSystemPersistenceConfiguration
import hwr.oop.examples.template.SqlPersistence
import hwr.oop.examples.template.config.ConfigLoader
import hwr.oop.examples.template.config.PersistenceType
import hwr.oop.rummikub_2026.adapters.`in`.DrawTileUseCase
import hwr.oop.rummikub_2026.adapters.`in`.LoadGameByIdQuery
import hwr.oop.rummikub_2026.adapters.`in`.NewGameUseCase
import hwr.oop.rummikub_2026.adapters.`in`.PlayTileUseCase
import hwr.oop.rummikub_2026.ports.out.GameRepository
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import okio.Path.Companion.toPath
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Config {
	
	private val appConfig = ConfigLoader.load()
	private val gamePersistence: GameRepository by lazy {
		when (appConfig.persistence) {
			PersistenceType.SQL -> SqlPersistence(
				appConfig.sql.jdbcUrl,
				appConfig.sql.username,
				appConfig.sql.password,
			)
			
			PersistenceType.FILE_SYSTEM -> FileSystemPersistence(
				configuration = FileSystemPersistenceConfiguration(
					directory = appConfig.fileSystem.directory.toPath()
				)
			)
		}
	}
	
	@Bean
	fun persistence(): GameRepository = gamePersistence
	
	@Bean
	fun newGameUseCase(saveGameUseCase: SaveGamePort) = NewGameUseCase(
		saveGamePort = saveGameUseCase
	)
	
	@Bean
	fun PlayTileUseCase(saveGameUseCase: SaveGamePort, loadGameByIdPort: LoadGameByIdPort) = PlayTileUseCase(
		loadGameByIdPort = loadGameByIdPort,
		saveGamePort = saveGameUseCase,
	)
	
	@Bean
	fun DrawTileUseCase(saveGameUseCase: SaveGamePort, loadGameByIdPort: LoadGameByIdPort) = DrawTileUseCase(
		loadGameByIdPort = loadGameByIdPort,
		saveGamePort = saveGameUseCase,
	)
	
	@Bean
	fun loadGameByIdQuery(loadGameByIdPort: LoadGameByIdPort) = LoadGameByIdQuery(
		loadGameByIdPort = loadGameByIdPort,
	)
}