package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Folge
import hwr.oop.rummikub_2026.core.Spiel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.InvalidObjectException
import hwr.oop.rummikub_2026.core.Spieler
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Tisch
import hwr.oop.rummikub_2026.core.Zahl

class SpielAuflosenTest {

    @Test
    fun `aufloesen - Exception bei leerer Kombination`() {
        val spieler = Spieler(
            "Max",
            "1",
            SpielTestData.create14Steine(),
            true
        )
        val leereKombi = Folge(mutableListOf())
        val tisch = Tisch(mutableListOf(leereKombi))
        val spiel = Spiel(
            spieler,
            emptyList(),
            tisch
        )
        assertThrows<InvalidObjectException> {
            spiel.aufloesen(
                leereKombi,
                spieler,
                tisch
            )
        }
    }

    @Test
    fun `aufloesen - Kombination wird vom Tisch entfernt`() {
        val spieler = Spieler(
            "Max",
            "1",
            SpielTestData.create14Steine(),
            true
        )

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spiel = Spiel(
            spieler,
            emptyList(),
            tisch
        )

        val neuesSpiel = spiel.aufloesen(
            kombi,
            spieler,
            tisch
        )

        assertFalse(neuesSpiel.tisch.tischReadOnly.contains(kombi))
    }

    @Test
    fun `aufloesen - Steine der Kombination landen in tmpListe`() {
        val spieler = Spieler(
            "Max",
            "1",
            SpielTestData.create14Steine(),
            true
        )

        val steine = mutableListOf(
            Stein(Farbe.Orange, Zahl.Zehn),
            Stein(Farbe.Orange, Zahl.Elf),
            Stein(Farbe.Orange, Zahl.Zwoelf)
        )

        val kombi = Folge(steine)

        val tisch = Tisch(mutableListOf(kombi))

        val spiel = Spiel(
            spieler,
            emptyList(),
            tisch
        )

        spiel.aufloesen(
            kombi,
            spieler,
            tisch
        )

        assertEquals(
            steine,
            tisch.tmpListe
        )
    }

    @Test
    fun `aufloesen - aktiver Spieler bleibt erhalten`() {
        val spieler = Spieler(
            "Max",
            "1",
            SpielTestData.create14Steine(),
            true
        )

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spiel = Spiel(
            spieler,
            emptyList(),
            tisch
        )

        val neuesSpiel = spiel.aufloesen(
            kombi,
            spieler,
            tisch
        )

        assertEquals(
            spieler.id,
            neuesSpiel.aktivSpieler.id
        )
    }

    @Test
    fun `aufloesen - ungueltiger Spieler wirft Exception`() {
        val spieler1 = Spieler(
            "Max",
            "1",
            SpielTestData.create14Steine(),
            true
        )

        val spieler2 = Spieler(
            "Moritz",
            "2",
            SpielTestData.create14Steine(),
            true
        )

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            tisch = tisch
        )

        val exception = assertThrows<InvalidObjectException> {
            spiel.aufloesen(
                kombi,
                spieler2,
                tisch
            )
        }

        assertThat(exception.message).contains("Spieler ist nicht an der Reihe!")
    }
}