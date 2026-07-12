package hwr.oop.examples.template

import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.ports.out.GameRepository
import hwr.oop.rummikub_2026.ports.out.LoadGameByIdPort
import hwr.oop.rummikub_2026.ports.out.SaveGamePort
import kotlinx.serialization.json.Json
import okio.FileNotFoundException
import okio.FileSystem
import okio.Path

private val json = Json {
	prettyPrint = true
	ignoreUnknownKeys = true
}

class FileSystemPersistence(
	configuration: FileSystemPersistenceConfiguration,
	private val fileSystem: FileSystem = FileSystem.SYSTEM,
) : GameRepository, SaveGamePort {
	
	private val directory = configuration.directory
	
	override fun save(spiel: Spiel) {
		val spielId = spiel.id
		val path = path(spielId)
		fileSystem.write(path) {
			writeUtf8(json.encodeToString<Spiel>(spiel))
		}
	}
	
	override fun loadByid(gameId: SpielId): Spiel {
		val path = path(gameId)
		val readString = try {
			fileSystem.read(path) {
				readUtf8()
			}
		} catch (e: FileNotFoundException) {
			throw LoadGameByIdPort.CouldNotLoadException(gameId, e)
		}
		
		return json.decodeFromString<Spiel>(readString)
	}
	
	private fun path(spielId: SpielId): Path {
		return directory / "${spielId.wert}.json"
	}
	
}

