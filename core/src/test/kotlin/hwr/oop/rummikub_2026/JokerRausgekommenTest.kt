package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.erstelle14Steine
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JokerRauskommenTest {
	@Test
	fun `Spieler kommt raus wenn Joker vorne steht`() {
		val steine = listOf(
			Stein(Farbe.Joker, Zahl.Eins),
			Stein(Farbe.Rot, Zahl.Fuenf),
			Stein(Farbe.Rot, Zahl.Sechs),
			Stein(Farbe.Rot, Zahl.Sieben),
			Stein(Farbe.Rot, Zahl.Acht)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
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
		
		assertThat(neuesSpiel.gesammeltePunkte)
			.isGreaterThanOrEqualTo(30)
	}
	
	@Test
	fun `Spieler kommt raus wenn Joker im Set die 30 Punkte erreicht`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Neun),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Schwarz, Zahl.Neun),
			Stein(Farbe.Joker, Zahl.Eins)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
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
		
		assertThat(neuesSpiel.gesammeltePunkte)
			.isGreaterThanOrEqualTo(30)
	}
	
	@Test
	fun `Joker liefert die fehlenden Punkte fuer das Rauskommen`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Acht),
			Stein(Farbe.Rot, Zahl.Neun),
			Stein(Farbe.Rot, Zahl.Zehn),
			Stein(Farbe.Joker, Zahl.Eins)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
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
		
		assertThat(neuesSpiel.aktivSpieler.rausgekommen).isTrue()
		
		assertThat(neuesSpiel.gesammeltePunkte).isEqualTo(38)
	}
	
	@Test
	fun `Spieler kommt nicht raus wenn Joker nicht genug Punkte bringt`() {
		val steine = listOf(
			Stein(Farbe.Joker, Zahl.Eins),
			Stein(Farbe.Rot, Zahl.Drei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Rot, Zahl.Fuenf)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
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
		
		assertThat(neuesSpiel.aktivSpieler.rausgekommen).isFalse()
	}
	
	@Test
	fun `Ungueltige Joker Folge verhindert Rauskommen`() {
		val steine = listOf(
			Stein(Farbe.Joker, Zahl.Eins),
			Stein(Farbe.Rot, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
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
		
		assertThat(spieler.rausgekommen).isFalse()
	}
	
	@Test
	fun `Ungueltiges Joker Set verhindert Rauskommen`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Zehn),
			Stein(Farbe.Blau, Zahl.Zehn),
			Stein(Farbe.Joker, Zahl.Eins)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
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
		
		assertThat(spieler.rausgekommen).isFalse()
	}
}