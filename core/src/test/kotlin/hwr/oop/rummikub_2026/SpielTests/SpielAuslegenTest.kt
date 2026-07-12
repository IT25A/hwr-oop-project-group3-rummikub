package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.*
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.erstelle14Steine
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.standardStein
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.stein2
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.stein3
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.InvalidObjectException

class SpielAuslegenTest {
	companion object {
		@JvmStatic
		fun ungueltigeAuslegenSzenarien() = SpielTestData.ungueltigeAuslegenSzenarien()
		
		@JvmStatic
		fun beutelSzenarien() = SpielTestData.beutelSzenarien()
		
		@JvmStatic
		fun gesammeltePunkteSzenarien() = SpielTestData.gesammeltePunkteSzenarien()
		
		@JvmStatic
		fun tmpListeAuslegenSzenarien() = SpielTestData.tmpListeAuslegenSzenarien()
		
		@JvmStatic
		fun tmpListeAuslegenMitTmpSteinenSzenarien() = SpielTestData.tmpListeAuslegenMitTmpSteinenSzenarien()
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeAuslegenSzenarien")
	fun `auslegen - ungueltiges auslegen wirft Exception`(
		testfall: Pair<Triple<List<Stein>, List<Stein>, Array<Stein>>, String>,
	) {
		val hand = testfall.first.first
		val list = testfall.first.second
		
		val spieler = Spieler(
			"Luxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*hand.toTypedArray()),
			true
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		val exception = assertThrows<InvalidObjectException> {
			spiel.auslegen(
				spieler = spieler,
				istSet = false,
				steine = list,
			)
		}
		
		assertThat(exception.message).contains(testfall.second)
	}
	
	@Test
	fun `auslegen - auslegen von ungueltigem Spieler wirft Exception`() {
		val spieler1 = Spieler(
			"Luxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(standardStein),
			true
		)
		
		val spieler2 = Spieler(
			"Smilla",
			SpielerId("2"),
			erstelle14Steine(),
			true
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler1,
			beutel = emptyList(),
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		val exception = assertThrows<InvalidObjectException> {
			spiel.auslegen(
				spieler = spieler2,
				istSet = false,
				steine = listOf(standardStein)
			)
		}
		
		assertThat(exception.message).contains("Spieler ist nicht an der Reihe!")
	}
	
	@Test
	fun `auslegen - gueltiges auslegen einer neuen Kombination funktioniert`() {
		val spieler = Spieler(
			"Luxi-Taxi",
			SpielerId("1"), erstelle14Steine(standardStein, stein2, stein3),
			true
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
			steine = listOf(standardStein, stein2, stein3)
		)
		
		assertThat(neuesSpiel.aktivSpieler.brettReadOnly).hasSize(11)
	}
	
	@ParameterizedTest
	@MethodSource("beutelSzenarien")
	fun `beutel wird korrekt initialisiert`(beutelSteine: List<Stein>) {
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine()
		)
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = beutelSteine,
			tisch = Tisch(mutableListOf()),
			listOf()
		)
		
		assertThat(spiel.beutel).isEqualTo(beutelSteine)
		assertThat(spiel.beutel).hasSize(beutelSteine.size)
	}
	
	@ParameterizedTest
	@MethodSource("gesammeltePunkteSzenarien")
	fun `gesammeltePunkte werde nbeim Auslegen korrekt berechnet`(
		testfall: Triple<List<Stein>, Boolean, Int>,
	) {
		val steine = testfall.first
		val istSet = testfall.second
		val erwartePunkte = testfall.third
		
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
			istSet = istSet,
			steine = steine
		)
		
		assertThat(neuesSpiel.gesammeltePunkte).isEqualTo(erwartePunkte)
	}
	
	@ParameterizedTest
	@MethodSource("tmpListeAuslegenSzenarien")
	fun `tmpListe wird beim Auslegen korrekt gesetzt`(
		testfall: Triple<List<Stein>, Boolean, Boolean>,
	) {
		val steine = testfall.first
		val istSet = testfall.second
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val tisch = Tisch(mutableListOf())
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = tisch,
			listOf()
		)
		
		spiel.auslegen(
			spieler = spieler,
			istSet = istSet,
			steine = steine
		)
		
		assertThat(tisch.tmpListe).isEmpty()
	}
	
	@ParameterizedTest
	@MethodSource("tmpListeAuslegenMitTmpSteinenSzenarien")
	fun `tmpListe wird beim Auslegen mit der vorhandenen tmpListe (Steinen) korrekt berechnet`(
		testfall: Triple<List<Stein>, List<Stein>, List<Stein>>,
	) {
		val handSteine = testfall.first
		val tmpSteine = testfall.second
		val erwarteteTmpListe = testfall.third
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*handSteine.toTypedArray())
		)
		
		val tisch = Tisch(mutableListOf())
		tisch.tmpListe = tmpSteine.toMutableList()
		
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = tisch,
			listOf()
		)
		
		spiel.auslegen(
			spieler = spieler,
			istSet = false,
			steine = handSteine
		)
		
		assertThat(tisch.tmpListe).containsExactlyInAnyOrderElementsOf(erwarteteTmpListe)
	}
	
	@Test
	fun `Rausgekommen wird auf true gesetzt wenn 30 Punkte erreicht werden`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Zehn),
			Stein(Farbe.Blau, Zahl.Zehn),
			Stein(Farbe.Schwarz, Zahl.Zehn)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
		)
		
		val tisch = Tisch(mutableListOf())
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = tisch,
			listOf()
		)
		
		val neuesSpiel = spiel.auslegen(
			spieler = spieler,
			istSet = true,
			steine = steine
		)
		assertThat(neuesSpiel.aktivSpieler.rausgekommen).isTrue()
	}
	
	@Test
	fun `Rausgekommen wird nicht true wenn weniger als 30 Punkte gelegt werden`() {
		val steine = listOf(
			Stein(Farbe.Rot, Zahl.Sieben),
			Stein(Farbe.Rot, Zahl.Acht),
			Stein(Farbe.Rot, Zahl.Neun)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray()),
			false
		)
		
		val tisch = Tisch(mutableListOf())
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = tisch,
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
	fun `auslegen fuegt Kombination zum Tisch hinzu`() {
		val steine = listOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Orange, Zahl.Zwei),
			Stein(Farbe.Orange, Zahl.Drei)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("1"),
			erstelle14Steine(*steine.toTypedArray())
		)
		
		val tisch = Tisch(mutableListOf())
		val spiel = Spiel(
			aktivSpieler = spieler,
			beutel = emptyList(),
			tisch = tisch,
			listOf()
		)
		
		spiel.auslegen(
			spieler = spieler,
			istSet = false,
			steine = steine
		)
		
		assertThat(tisch.tischReadOnly).hasSize(1)
		assertThat(tisch.tischReadOnly[0].get()).containsExactlyInAnyOrder(*steine.toTypedArray())
	}
}