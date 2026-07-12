package hwr.oop.rummikub_2026.TischTests

import hwr.oop.rummikub_2026.core.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class TischAuflosenTest {
	
	companion object {
		@JvmStatic
		fun kombinationen() = TischTestData.kombinationen()
	}
	
	@ParameterizedTest
	@MethodSource("kombinationen")
	fun `alle Steine einer Kombination werden in tmpListe uebernommen und Kombination wird vom Tisch geloescht`(
		kombination: Kombinationen,
	) {
		val tisch = Tisch(
			mutableListOf(kombination)
		)
		
		val tischZuvor = tisch.tischReadOnly
		val tmpListeLaenge = tisch.tmpListe.size
		
		tisch.aufloesen(kombination)
		
		assertEquals(
			kombination.get(),
			tisch.tmpListe
		)
		
		assertEquals(
			tischZuvor - tisch.tischReadOnly,
			listOf(kombination)
		)
		
		assertEquals(
			tisch.tmpListe.size,
			tmpListeLaenge + kombination.get().size
		)
	}
	
	@ParameterizedTest
	@ValueSource(ints = [0, 1, 2])
	fun `Kombination an beliebiger Position kann aufgeloest werden`(
		index: Int,
	) {
		val k1 = Folge(
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Rot, Zahl.Fuenf)
			)
		)
		
		val k2 = Sets(
			mutableListOf(
				Stein(Farbe.Blau, Zahl.Sieben),
				Stein(Farbe.Schwarz, Zahl.Sieben),
				Stein(Farbe.Orange, Zahl.Sieben)
			)
		)
		
		val k3 = Folge(
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Zehn),
				Stein(Farbe.Orange, Zahl.Elf),
				Stein(Farbe.Orange, Zahl.Zwoelf)
			)
		)
		
		val kombis = mutableListOf(k1, k2, k3)
		val target = kombis[index]
		val tisch = Tisch(kombis)
		
		tisch.aufloesen(target)
		
		assertFalse(tisch.tischReadOnly.contains(target))
	}
	
	@Test
	fun `Exception wenn Kombination nicht auf dem Tisch liegt`() {
		val vorhandene = Folge(
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Eins),
				Stein(Farbe.Rot, Zahl.Zwei),
				Stein(Farbe.Rot, Zahl.Drei)
			)
		)
		
		val nichtVorhandene = Folge(
			mutableListOf(
				Stein(Farbe.Blau, Zahl.Elf),
				Stein(Farbe.Blau, Zahl.Zwoelf),
				Stein(Farbe.Blau, Zahl.Dreizehn)
			)
		)
		
		val tisch = Tisch(mutableListOf(vorhandene))
		
		assertThrows<IllegalArgumentException> {
			tisch.aufloesen(nichtVorhandene)
		}
	}
	
	@Test
	fun `bei zwei identischen Folgen bleibt eine erhalten`() {
		val folge = Folge(
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Rot, Zahl.Fuenf)
			)
		)
		
		val tisch = Tisch(mutableListOf(folge, folge))
		
		tisch.aufloesen(folge)
		
		assertEquals(1, tisch.tischReadOnly.size)
		assertTrue(tisch.tischReadOnly.contains(folge))
	}
}