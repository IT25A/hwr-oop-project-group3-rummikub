package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Folge
import hwr.oop.rummikub_2026.core.Kombinationen
import hwr.oop.rummikub_2026.core.Sets
import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.Spieler
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Tisch
import hwr.oop.rummikub_2026.core.Zahl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class `30PunkteTest` {
	
	private val rausgekommenerSpieler = Spieler(
		"Rausgekommener Spieler",
		"1",
		create14Steine(Stein(Farbe.Rot, Zahl.Zwei)),
		true
	)
	
	private val nichtRausgekommenerSpieler = Spieler(
		"Nicht Rausgekommener Spieler",
		"2",
		create14Steine(Stein(Farbe.Rot, Zahl.Zwei))
	)
	
	private fun create14Steine(vararg zusaetzlicheSteine: Stein): MutableList<Stein> {
		val steine = zusaetzlicheSteine.toMutableList()
		while (steine.size < 14) {
			steine.add(Stein(Farbe.Schwarz, Zahl.Eins))
		}
		return steine
	}
	
	companion object {
		@JvmStatic
		fun gueltigeKombiAnlegenRotZwei() = listOf(
			Folge(
				mutableListOf(
					Stein(Farbe.Rot, Zahl.Drei),
					Stein(Farbe.Rot, Zahl.Vier),
					Stein(Farbe.Rot, Zahl.Fuenf)
				)
			),
			Sets(
				mutableListOf(
					Stein(Farbe.Schwarz, Zahl.Zwei),
					Stein(Farbe.Blau, Zahl.Zwei),
					Stein(Farbe.Orange, Zahl.Zwei)
				)
			)
		)
		
		@JvmStatic
		fun verschiedeneSpieler() = listOf(
			Spieler(
				"Rausgekommener Spieler",
				"1",
				mutableListOf(
					Stein(Farbe.Rot, Zahl.Zwei),
					Stein(Farbe.Schwarz, Zahl.Fuenf),
					Stein(Farbe.Blau, Zahl.Fuenf),
					Stein(Farbe.Rot, Zahl.Fuenf)
				),
				true,
				false
			),

			Spieler(
				"Nicht Rausgekommener Spieler",
				"2",
				mutableListOf(
					Stein(Farbe.Rot, Zahl.Zwei),
					Stein(Farbe.Schwarz, Zahl.Fuenf),
					Stein(Farbe.Blau, Zahl.Fuenf),
					Stein(Farbe.Rot, Zahl.Fuenf)
				),
				false,
				false
			)
		)
		
		@JvmStatic
		fun zweiKombisUeberDreissig() = listOf(
			Pair(
				Sets(
					mutableListOf(
						Stein(Farbe.Rot, Zahl.Drei),
						Stein(Farbe.Blau, Zahl.Drei),
						Stein(Farbe.Schwarz, Zahl.Drei),
					)
				),
				Folge(
					mutableListOf(
						Stein(Farbe.Rot, Zahl.Sieben),
						Stein(Farbe.Rot, Zahl.Acht),
						Stein(Farbe.Rot, Zahl.Neun)
					)
				)
			),
			Pair(
				Sets(
					mutableListOf(
						Stein(Farbe.Orange, Zahl.Sieben),
						Stein(Farbe.Blau, Zahl.Sieben),
						Stein(Farbe.Schwarz, Zahl.Sieben),
					)
				),
				Folge(
					mutableListOf(
						Stein(Farbe.Rot, Zahl.Sieben),
						Stein(Farbe.Rot, Zahl.Acht),
						Stein(Farbe.Rot, Zahl.Neun)
					)
				)
			)
		)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeKombiAnlegenRotZwei")
	fun `Anlegen geht wenn rausgekommen`(
		kombi: Kombinationen,
  ) {
		//given
		val spiel = Spiel(
			rausgekommenerSpieler,
			emptyList(),
			Tisch(mutableListOf(kombi)),
			listOf()
		)
		//when
		spiel.anlegen(
			rausgekommenerSpieler,
			Stein(Farbe.Rot, Zahl.Zwei),
			0,
			spiel.tisch
		)
		//then
		assertEquals(
			kombi.get() + mutableListOf(Stein(Farbe.Rot, Zahl.Zwei)),
			spiel.tisch.tischReadOnly[0].get()
		)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeKombiAnlegenRotZwei")
	fun `Anlegen geht nicht wenn nicht rausgekommen`(
		kombi: Kombinationen,
  ) {
		val spiel = Spiel(
			nichtRausgekommenerSpieler,
			emptyList(),
			Tisch(mutableListOf(kombi)),
			listOf()
		)
		
		val exception = assertThrows<IllegalArgumentException> {
			spiel.anlegen(
				nichtRausgekommenerSpieler,
				Stein(Farbe.Rot, Zahl.Zwei),
				0,
				spiel.tisch
			)
		}
		
		assertThat(exception.message).contains("Du musst erst rauskommen, bevor du anlegen kannst!")
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeKombiAnlegenRotZwei")
	fun `Aufloesen geht wenn rausgekommen`(
		kombi: Kombinationen,
  ) {
		val spiel = Spiel(
			rausgekommenerSpieler,
			emptyList(),
			Tisch(mutableListOf(kombi)),
			listOf()
		)
		
		spiel.aufloesen(
			kombi,
			rausgekommenerSpieler,
			spiel.tisch
		)
		
		assertEquals(
			kombi.get(),
			spiel.tisch.tmpListe
		)
		
		assertFalse(spiel.tisch.tischReadOnly.contains(kombi))
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeKombiAnlegenRotZwei")
	fun `Aufloesen geht nicht wenn nicht rausgekommen`(
		kombi: Kombinationen,
  ) {
		val spiel = Spiel(
			nichtRausgekommenerSpieler,
			emptyList(),
			Tisch(mutableListOf(kombi)),
			listOf()
		)
		
		val exception = assertThrows<IllegalArgumentException> {
			spiel.aufloesen(
				kombi,
				nichtRausgekommenerSpieler,
				spiel.tisch
			)
		}
		
		assertThat(exception.message).contains("Du musst erst rauskommen, bevor du aufloesen kannst!")
	}
	
	@ParameterizedTest
	@MethodSource("verschiedeneSpieler")
	fun `Ziehen geht immer`(
		spieler: Spieler,
  ) {
		val spiel = Spiel(
			spieler,
			listOf(Stein(Farbe.Schwarz, Zahl.Fuenf)),
			Tisch(mutableListOf()),
			listOf()
		)
		// when
		val neuesSpiel = spiel.ziehen(spieler)
		
		// then
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).contains(Stein(Farbe.Schwarz, Zahl.Fuenf))
		
		assertThat(neuesSpiel.beutel).isEmpty()
	}
	
	@ParameterizedTest
	@MethodSource("verschiedeneSpieler")
	fun `Auslegen geht immer`(
		spieler: Spieler,
  ) {
		val spiel = Spiel(
			spieler,
			listOf(Stein(Farbe.Schwarz, Zahl.Fuenf)),
			Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spieler,
			true,
			spiel.tisch,
			Stein(Farbe.Schwarz, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Fuenf),
			Stein(Farbe.Rot, Zahl.Fuenf)
		)
		
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).doesNotContain(
			Stein(Farbe.Schwarz, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Fuenf),
			Stein(Farbe.Rot, Zahl.Fuenf)
		)
		assertThat(neuesSpiel.tisch.tischReadOnly).contains(
			Sets(
				mutableListOf(
					Stein(Farbe.Schwarz, Zahl.Fuenf),
					Stein(Farbe.Blau, Zahl.Fuenf),
					Stein(Farbe.Rot, Zahl.Fuenf)
				)
			)
		)
	}
	
	@Test
	fun `Wenn genau 30 Punkte gelegt wurden , ist Spieler rausgekommen`() {
		val spiel = Spiel(
			Spieler(
				"spielerx",
				"4",
				mutableListOf(
					Stein(Farbe.Schwarz, Zahl.Zehn),
					Stein(Farbe.Blau, Zahl.Zehn),
					Stein(Farbe.Rot, Zahl.Zehn)
				),
				false,
				false
			),
			emptyList(),
			Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spiel.aktivSpieler,
			true,
			spiel.tisch,
			Stein(Farbe.Schwarz, Zahl.Zehn),
			Stein(Farbe.Blau, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Zehn)
		)
		
		assertEquals(
			true,
			neuesSpiel.aktivSpieler.rausgekommen
		)
	}
	
	@Test
	fun `Wenn weniger als 30 Punkte gelegt wurden , ist Spieler nicht rausgekommen`() {
		val spiel = Spiel(
			Spieler(
				"spielerx",
				"4",
				mutableListOf(
					Stein(Farbe.Schwarz, Zahl.Neun),
					Stein(Farbe.Blau, Zahl.Neun),
					Stein(Farbe.Rot, Zahl.Neun)
				),
				false,
				false
			),
			emptyList(),
			Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spiel.aktivSpieler,
			true,
			spiel.tisch,
			Stein(Farbe.Schwarz, Zahl.Neun),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Rot, Zahl.Neun)
		)
		
		assertEquals(
			false,
			neuesSpiel.aktivSpieler.rausgekommen
		)
	}
	
	@Test
	fun `Wenn mehr als 30 Punkte gelegt wurden , ist Spieler rausgekommen`() {
		val spiel = Spiel(
			Spieler(
				"spielerx",
				"4",
				mutableListOf(Stein(Farbe.Schwarz, Zahl.Elf), Stein(Farbe.Blau, Zahl.Elf), Stein(Farbe.Rot, Zahl.Elf)),
				false,
				false
			),
			emptyList(), Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spiel.aktivSpieler,
			true,
			spiel.tisch,
			Stein(Farbe.Schwarz, Zahl.Elf), Stein(Farbe.Blau, Zahl.Elf), Stein(Farbe.Rot, Zahl.Elf)
		)
		
		assertEquals(
			true,
			neuesSpiel.aktivSpieler.rausgekommen
		)
	}
	
	@ParameterizedTest
	@MethodSource("zweiKombisUeberDreissig")
	fun `Wenn mehrere Kombinationen zusammen 30 Punkte ergeben , ist Spieler rausgekommen`(
		kombis: Pair<Kombinationen, Kombinationen>,
  ) {
		val spieler = Spieler(
			"spielerx",
			"4",
			(kombis.first.get() + kombis.second.get()).toMutableList(),
			false,
			false
		)
		
		val spiel = Spiel(
			spieler,
			emptyList(),
			Tisch(mutableListOf()),
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spiel.aktivSpieler,
			true,
			spiel.tisch,
			*kombis.first.get().toTypedArray()
		)
		
		val neueresSpiel = neuesSpiel.auslegen(
			neuesSpiel.aktivSpieler,
			false,
			neuesSpiel.tisch,
			*kombis.second.get().toTypedArray()
		)
		
		assertEquals(true, neueresSpiel.aktivSpieler.rausgekommen)
	}
}