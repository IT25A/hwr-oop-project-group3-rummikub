package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.erstelle14Steine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class JokerTest {
	@Test
	fun `Joker koennen existieren`() {
		val farben = Farbe.entries
		
		assertThat(farben).contains(
			Farbe.Joker
		)
	}
	
	@Test
	fun `Beutel haben anfangs Joker`() {
		val beutel = hwr.oop.rummikub_2026.core.Beutel()
		
		assertThat(beutel.beutelReadOnly.map { it.farbe() }).contains(
			Farbe.Joker
		)
	}
	
	companion object {
		@JvmStatic
		fun gueltigeFolgen() = listOf(
			//erste
			mutableListOf(
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Rot, Zahl.Zwei),
				Stein(Farbe.Rot, Zahl.Drei)
			),
			//letzte
			mutableListOf(
				Stein(Farbe.Blau, Zahl.Fuenf),
				Stein(Farbe.Blau, Zahl.Sechs),
				Stein(Farbe.Blau, Zahl.Sieben),
				Stein(Farbe.Joker, Zahl.Eins)
			),
			//mitte
			mutableListOf(
				Stein(Farbe.Schwarz, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Schwarz, Zahl.Vier),
				Stein(Farbe.Schwarz, Zahl.Fuenf),
				Stein(Farbe.Schwarz, Zahl.Sechs),
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Acht),
				Stein(Farbe.Schwarz, Zahl.Neun),
				Stein(Farbe.Schwarz, Zahl.Zehn),
				Stein(Farbe.Schwarz, Zahl.Elf),
				Stein(Farbe.Schwarz, Zahl.Zwoelf),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			),
			//zwei nacheinander
			mutableListOf(
				Stein(Farbe.Schwarz, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Schwarz, Zahl.Vier),
				Stein(Farbe.Schwarz, Zahl.Fuenf),
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Acht),
				Stein(Farbe.Schwarz, Zahl.Neun),
				Stein(Farbe.Schwarz, Zahl.Zehn),
				Stein(Farbe.Schwarz, Zahl.Elf),
				Stein(Farbe.Schwarz, Zahl.Zwoelf),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			),
			//zwei getrennt
			mutableListOf(
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Schwarz, Zahl.Vier),
				Stein(Farbe.Schwarz, Zahl.Fuenf),
				Stein(Farbe.Schwarz, Zahl.Sechs),
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Acht),
				Stein(Farbe.Schwarz, Zahl.Neun),
				Stein(Farbe.Schwarz, Zahl.Zehn),
				Stein(Farbe.Schwarz, Zahl.Elf),
				Stein(Farbe.Schwarz, Zahl.Zwoelf),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			)
		)
		
		@JvmStatic
		fun gueltigeSets() = listOf(
			mutableListOf(
				Stein(Farbe.Joker, Zahl.Eins),
				Stein(Farbe.Rot, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Eins)
			),
			mutableListOf(
				Stein(Farbe.Joker, Zahl.Sechs),
				Stein(Farbe.Joker, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sechs)
			),
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Dreizehn),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Joker, Zahl.Dreizehn)
			)
		)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeFolgen")
	fun `gueltige Folgen werfen keine Exception`(
		steine: MutableList<Stein>,
	) {
		val folge = Folge(steine)
		
		assertDoesNotThrow {
			folge.istGueltig()
		}
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeSets")
	fun `gueltige Sets werfen keine Exception`(
		steine: MutableList<Stein>,
	) {
		val folge = Sets(steine)
		
		assertDoesNotThrow {
			folge.istGueltig()
		}
	}
	
	@Test
	fun `auslegen - gueltige Folge mit Joker in der Mitte funktioniert`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Fuenf),
			Stein(Farbe.Joker, Zahl.Eins),
			Stein(Farbe.Rot, Zahl.Sieben)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spieler = spieler,
			istSet = false,
			steine = steine
		)
		
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).hasSize(11)
	}
	
	@Test
	fun `auslegen - gueltige Folge mit Joker am Anfang funktioniert`() {
		val steine = listOf(
			Stein(Farbe.Joker, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Sechs),
			Stein(Farbe.Blau, Zahl.Sieben)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spieler = spieler,
			istSet = false,
			steine = steine
		)
		
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).hasSize(11)
	}
	
	@Test
	fun `auslegen - gueltiges Set mit Joker funktioniert`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Acht),
			Stein(Farbe.Blau, Zahl.Acht),
			Stein(Farbe.Joker, Zahl.Eins)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spieler = spieler,
			istSet = true,
			steine = steine
		)
		
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).hasSize(11)
	}
	
	@Test
	fun `auslegen - ungueltige Folge mit Joker wirft Exception`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Fuenf),
			Stein(Farbe.Joker, Zahl.Eins),
			Stein(Farbe.Rot, Zahl.Neun)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		assertThrows<IllegalArgumentException> {
			spiel.auslegen(
				spieler = spieler,
				istSet = false,
				steine = steine
			)
		}
	}
	
	@Test
	fun `auslegen - ungueltiges Set mit Joker wirft Exception`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Drei),
			Stein(Farbe.Rot, Zahl.Drei),
			Stein(Farbe.Joker, Zahl.Eins)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		assertThrows<IllegalArgumentException> {
			spiel.auslegen(
				spieler = spieler,
				istSet = true,
				steine = steine
			)
		}
	}
}