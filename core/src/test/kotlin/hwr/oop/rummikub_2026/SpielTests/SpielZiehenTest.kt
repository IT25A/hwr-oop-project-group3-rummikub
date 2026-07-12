package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.erstelle14Steine
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.steinRot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.InvalidObjectException

class SpielZiehenTest {
	companion object {
		@JvmStatic
		fun gueltigeZiehenSzenarien() = SpielTestData.gueltigeZiehenSzenarien()
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeZiehenSzenarien")
	fun `ziehen - gueltiger Spieler zieht einen Stein aus dem Beutel`(
		testfall: Pair<List<Stein>, Int>,
	) {
		val spieler = Spieler(
			"Luxi-Taxi",
			SpielerId("1"),
			erstelle14Steine()
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = testfall.first,
			tisch = Tisch(mutableListOf()),
			spieler = listOf()
		)
		
		val neuesSpiel = spiel.ziehen(spieler)
		
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).hasSize(testfall.second)
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).contains(testfall.first.first())
		assertThat(neuesSpiel.beutel).isEmpty()
	}
	
	@Test
	fun `ziehen - ungueltiger Spieler darf nicht ziehen wirft Exception`() {
		val spieler1 = Spieler(
			"Luxi-Taxi",
			SpielerId("1"),
			erstelle14Steine()
		)
		
		val spieler2 = Spieler(
			"Maxi-Taxi",
			SpielerId("2"),
			erstelle14Steine()
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler1,
			beutel = listOf(steinRot),
			tisch = Tisch(mutableListOf()),
			spieler = listOf(),
		)
		
		val exception = assertThrows<InvalidObjectException> {
			spiel.ziehen(spieler2)
		}
		
		assertThat(exception.message).contains("Spieler ist nicht an der Reihe!")
	}
	
	@Test
	fun `ziehen - Ziehen bei leerem Beutel wirft Exception`() {
		val spieler = Spieler(
			"Luxi-Taxi",
			SpielerId("1"),
			erstelle14Steine()
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			spieler = listOf()
		)
		
		val exception = assertThrows<IllegalStateException> {
			spiel.ziehen(spieler)
		}
		
		assertThat(exception.message).contains("Der Beutel ist leer!")
	}
}