package hwr.oop.examples.template


import com.zaxxer.hikari.HikariDataSource
import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.SpielId
import hwr.oop.rummikub_2026.ports.out.SpielRepository
import hwr.oop.rummikub_2026.ports.out.SpielLadenByIdPort
import hwr.oop.rummikub_2026.ports.out.SpielSpeichernPort
import liquibase.Liquibase
import liquibase.Scope
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.logging.core.NoOpLogService
import liquibase.resource.ClassLoaderResourceAccessor
import liquibase.ui.LoggerUIService
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import javax.sql.DataSource
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.v1.core.dao.id.java.UUIDTable
import org.jetbrains.exposed.v1.json.jsonb

private val format = Json {
	prettyPrint = false
	isLenient = true
	ignoreUnknownKeys = true
}

object RummikubSpieleTabelle : UUIDTable("rummikub_spiele") {
	val game = jsonb<Spiel>("game", format)
}

class SqlPersistence(private val dataSource: DataSource) : SpielRepository{
	
	constructor(jdbcUrl: String, username: String, password: String) : this(
		HikariDataSource().apply {
			setJdbcUrl(jdbcUrl)
			setUsername(username)
			setPassword(password)
		}
	)
	
	init {
		runLiquibaseMigrations()
		Database.connect(dataSource)
	}
	
	private fun runLiquibaseMigrations() {
		System.setProperty("liquibase.command.update.showSummary", "OFF")
		val scopeAttrs = mapOf(
			Scope.Attr.logService.name to NoOpLogService(),
			Scope.Attr.ui.name to LoggerUIService(),
		)
		Scope.child(scopeAttrs) {
			dataSource.connection.use { connection ->
				val database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(JdbcConnection(connection))
				Liquibase(
					"db/changelog/db.changelog-master.yaml",
					ClassLoaderResourceAccessor(),
					database
				).update("")
			}
		}
	}
	override fun save(game: Spiel) {
		val gameId = game.id
		transaction {
			RummikubSpieleTabelle.insert {
				it[id] = gameId.uuid()
				it[this.game] = game
			}
		}
	}
	override fun loadByid(gameId: SpielId): Spiel {
		val javaUUID = gameId.uuid()
		val result = transaction {
			RummikubSpieleTabelle.select(RummikubSpieleTabelle.game)
				.where { RummikubSpieleTabelle.id eq javaUUID }.withDistinct()
				.map { it[RummikubSpieleTabelle.game] }
				.firstOrNull()
		}
		return result ?: throw SpielLadenByIdPort.CouldNotLoadException(gameId)
	}
	
}

