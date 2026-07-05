package hwr.oop.examples.template

import hwr.oop.rummikub_2026.core.TestData
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test


class FileSystemPersistenceTest {
	
	private val fakeFileSystem = FakeFileSystem()
	private val tempDir = "/tmp/template-test".toPath()
	private val sut: FileSystemPersistence
	
	init {
		fakeFileSystem.createDirectories(tempDir)
		sut = FileSystemPersistence(
			FileSystemPersistenceConfiguration(tempDir),
			fakeFileSystem
		)
	}

	private val spiel = TestData().testspiel
	private val gameId = spiel.id

	@Test
	fun `kann Spiel speichern`() {
		// when
		sut.save(spiel)
		val loaded = sut.loadByid(gameId)

		// then
		assertThat(loaded).isEqualTo(spiel)
	}

	@Test
	fun `Fehlerfall`() {
		// when / then
		assertThatThrownBy {
			sut.loadByid(gameId)
		}.hasMessageContainingAll("Could not load game", gameId.toString())
	}


	@AfterEach
	fun tearDown() {
		fakeFileSystem.checkNoOpenFiles()
	}

}

