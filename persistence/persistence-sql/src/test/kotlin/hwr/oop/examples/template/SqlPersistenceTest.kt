package hwr.oop.examples.template

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import hwr.oop.rummikub_2026.core.TestData
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class SqlPersistenceTest {
	
	companion object {
	  @Container
		@JvmStatic
		val postgres = PostgreSQLContainer("postgres:17-alpine")
	}
	
	private lateinit var adapter: SqlPersistence
	private lateinit var dataSource: HikariDataSource
	
	@BeforeEach
	fun setUp() {
		val config = HikariConfig().apply {
			jdbcUrl = postgres.jdbcUrl
			username = postgres.username
			password = postgres.password
		}
		dataSource = HikariDataSource(config)
		adapter = SqlPersistence(dataSource)
	}
	
	@AfterEach
	fun tearDown() {
		if (::dataSource.isInitialized) {
			dataSource.close()
		}
	}

	private val spiel = TestData().testspiel
	private val gameId = spiel.id

	@Test
	fun `kann Spiel speichern`() {
		// when
		adapter.save(spiel)
		val loaded = adapter.loadByid(gameId)

		// then
		assertThat(loaded).isEqualTo(spiel)
	}

	@Test
	fun `Fehlerfall`() {
		// when / then
		assertThatThrownBy {
			adapter.loadByid(gameId)
		}.hasMessageContainingAll("Could not load game", gameId.toString())
	}
}

