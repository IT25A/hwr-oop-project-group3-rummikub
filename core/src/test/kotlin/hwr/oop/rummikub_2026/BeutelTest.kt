package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.Beutel
import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Zahl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BeutelTest {

	@Test
	fun `Beutel kann existieren`() {
		val beutel = Beutel()
		
		assertNotNull(
			beutel,
			"Der Beutel sollte existieren und nicht null sein."
		)
	}
	
	@Test
	fun `Beutel hat 104 Steine`() {
		val beutel = Beutel()
		
		val erwarteteAnzahl = Farbe.entries.size * Zahl.entries.size * 2 + 2 - 26// 4 * 13 * 2 = 104
		
		assertEquals(
			erwarteteAnzahl,
			beutel.anzahlSteine(),
			"Der Beutel enthaelt 106 Steine ."
		)
	}
	
	@Test
	fun `Beutel hat jede Farb-Zahl-Kombination 2x`() {
		val beutel = Beutel()
		val alleSteine = mutableListOf<Stein>()
		
		while (!beutel.istLeer()) {
			alleSteine.add(beutel.zieheSteinAusBeutel()!!)
		}
		val alleSteineOhneJoker = alleSteine.filter {
			it.farbe() != Farbe.Joker
		}
		for (farbe in Farbe.entries.filter { it != Farbe.Joker }) {
			for (zahl in Zahl.entries.filter { it != Zahl.Eins }) {
				val anzahl = alleSteineOhneJoker.count {
					it.farbe() == farbe && it.zahl() == zahl
				}

				assertEquals(2, anzahl)
			}
		}
	}
	
	@Test
	fun `zieheStein gibt einen Stein zurueck und die Anzahl wird um 1 verringert`() {
		val beutel = Beutel()
		val startAnzahl = beutel.anzahlSteine()
		
		val gezogenerStein = beutel.zieheSteinAusBeutel()
		
		assertNotNull(
			gezogenerStein,
			"Es sollte ein Stein gezogen werden."
		)
		
		assertEquals(
			startAnzahl - 1,
			beutel.anzahlSteine(),
			"Die Anzahl der Steine im Beutel ist um 1 gesunken."
		)
	}
	
	@Test
	fun `zieheSteinausBeutel sollte null zurueckgeben, wenn der Beutel leer ist`() {
		val beutel = Beutel()
		
		while (!beutel.istLeer()) {
			beutel.zieheSteinAusBeutel()
		}
		
		val steinAusLeeremBeutel = beutel.zieheSteinAusBeutel()
		
		assertNull(
			steinAusLeeremBeutel,
			"Wenn der Beutel leer ist, wird null zurueckgegeben."
		)
		
		assertTrue(
			beutel.istLeer(),
			"Der Beutel sollte weiterhin leer sein."
		)
	}
	
	@Test
	fun `istLeer sollte true zurueckgeben, wenn alle Steine gezogen wurden`() {
		val beutel = Beutel()
		
		assertFalse(
			beutel.istLeer(),
			"Der Beutel sollte am Anfang nicht leer sein."
		)
		
		repeat(106) {
			beutel.zieheSteinAusBeutel()
		}
		println("Steine nach 106 Zügen: ${beutel.anzahlSteine()}")
		
		assertTrue(
			beutel.istLeer(),
			"Nach dem Ziehen aller 106 Steine sollte der Beutel leer sein."
		)
	}
	
	@Test
	fun `anzahlSteine sollte korrekt dekrementieren`() {
		val beutel = Beutel()
		
		assertEquals(
			106,
			beutel.anzahlSteine()
		)
		
		beutel.zieheSteinAusBeutel()
		assertEquals(
			105,
			beutel.anzahlSteine()
		)
		
		beutel.zieheSteinAusBeutel()
		assertEquals(
			104,
			beutel.anzahlSteine()
		)
	}
	
	@Test
	fun `Steine im Beutel sollten gemischt sein`() {
		// Erstelle mehrere Beutel und prüfe, ob die Reihenfolge unterschiedlich ist
		val beutel1 = Beutel()
		val beutel2 = Beutel()
		
		val steineAusBeutel1 = mutableListOf<Stein>()
		val steineAusBeutel2 = mutableListOf<Stein>()
		
		// Ziehe die ersten 10 Steine aus beiden Beuteln
		repeat(10) {
			steineAusBeutel1.add(beutel1.zieheSteinAusBeutel()!!)
			steineAusBeutel2.add(beutel2.zieheSteinAusBeutel()!!)
		}
		
		val sindIdentisch = steineAusBeutel1.zip(steineAusBeutel2).all { (s1, s2) ->
			s1.farbe() == s2.farbe() && s1.zahl() == s2.zahl()
		}
		
		assertFalse(
			sindIdentisch,
			"Die Steine sollten gemischt sein. Zwei unabhängige Beutel sollten mit sehr hoher Wahrscheinlichkeit unterschiedliche Reihenfolgen haben."
		)
	}
}