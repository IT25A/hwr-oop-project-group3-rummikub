package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BeutelTest {

    @Test
    fun `Beutel kann existieren`() {
        val beutel = Beutel()

        assertNotNull(beutel, "Der Beutel sollte existieren und nicht null sein.")
    }

    @Test
    fun `Beutel hat 104 Steine`() {
        val beutel = Beutel()

        val erwarteteAnzahl = Farbe.entries.size * Zahl.entries.size * 2 // 4 * 13 * 2 = 104

        assertEquals(erwarteteAnzahl, beutel.anzahlSteine(), "Der Beutel enthaelt 104 Steine .")
    }

    @Test
    fun `Beutel hat jede Farb-Zahl-Kombination 2x`() {
        val beutel = Beutel()
        val alleSteine = mutableListOf<Stein>()

        while (!beutel.istLeer()) {
            alleSteine.add(beutel.zieheSteinausBeutel()!!)
        }

        for (farbe in Farbe.entries) {
            for (zahl in Zahl.entries) {
                val anzahl = alleSteine.count { it.farbe() == farbe && it.zahl() == zahl }
                assertEquals(2, anzahl, "Es sollte genau 2 Steine mit Farbe $farbe und Zahl $zahl geben.")
            }
        }
    }

    @Test
    fun `zieheStein gibt einen Stein zurueck und die Anzahl wird um 1 verringert`() {
        val beutel = Beutel()
        val startAnzahl = beutel.anzahlSteine()

        val gezogenerStein = beutel.zieheSteinausBeutel()

        assertNotNull(gezogenerStein, "Es sollte ein Stein gezogen werden.")
        assertEquals(startAnzahl - 1, beutel.anzahlSteine(), "Die Anzahl der Steine im Beutel ist um 1 gesunken.")
    }

    @Test
    fun `zieheSteinausBeutel sollte null zurueckgeben, wenn der Beutel leer ist`() {
        val beutel = Beutel()
        while (!beutel.istLeer()) {
            beutel.zieheSteinausBeutel()
        }

        val steinAusLeeremBeutel = beutel.zieheSteinausBeutel()

        assertNull(steinAusLeeremBeutel, "Wenn der Beutel leer ist, wird null zurückgegeben.")
        assertTrue(beutel.istLeer(), "Der Beutel sollte weiterhin leer sein.")
    }

    @Test
    fun `istLeer sollte true zurueckgeben, wenn alle Steine gezogen wurden`() {
        val beutel = Beutel()

        assertFalse(beutel.istLeer(), "Der Beutel sollte am Anfang nicht leer sein.")

        repeat(104) {
            beutel.zieheSteinausBeutel()
        }

        assertTrue(beutel.istLeer(), "Nach dem Ziehen aller 104 Steine sollte der Beutel leer sein.")
    }

    @Test
    fun `anzahlSteine sollte korrekt dekrementieren`() {
        val beutel = Beutel()

        assertEquals(104, beutel.anzahlSteine())

        beutel.zieheSteinausBeutel()
        assertEquals(103, beutel.anzahlSteine())

        beutel.zieheSteinausBeutel()
        assertEquals(102, beutel.anzahlSteine())
    }
}