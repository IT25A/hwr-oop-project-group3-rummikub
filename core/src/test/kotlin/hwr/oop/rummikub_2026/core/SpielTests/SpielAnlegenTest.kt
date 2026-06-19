package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Folge
import hwr.oop.rummikub_2026.core.Kombinationen
import hwr.oop.rummikub_2026.core.Spiel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.InvalidObjectException
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.create14Steine
import hwr.oop.rummikub_2026.core.Spieler
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Tisch
import hwr.oop.rummikub_2026.core.Zahl

class SpielAnlegenTest {

    companion object {
        @JvmStatic
        fun steine() = SpielTestData.steine()

        @JvmStatic
        fun tmpListeAnlegenSzenarien() = SpielTestData.tmpListeAnlegenSzenarien()

        @JvmStatic
        fun tmpListeAnlegenMitTmpSteinenSzenarien() = SpielTestData.tmpListeAnlegenMitTmpSteinenSzenarien()
    }

    @Test
    fun `anlegen - ungueltiger Spieler wirft Exception`() {
        val spieler1 = Spieler(
            "Max",
            "1",
            create14Steine(),
            true
        )
        val spieler2 = Spieler(
            "Moritz",
            "2",
            create14Steine(),
            true
        )

        val stein = Stein(Farbe.Rot, Zahl.Eins)

        val tisch = Tisch(mutableListOf())

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            tisch = tisch
        )

        val exception = assertThrows<InvalidObjectException> {
            spiel.anlegen(
                spieler = spieler2,
                neuStein = stein,
                kombi = 0,
                aktuellerTisch = tisch
            )
        }

        assertThat(exception.message).contains("Spieler ist nicht an der Reihe!")
    }

    @Test
    fun `anlegen - Stein nicht vorhanden wirft Exception`() {
        val steinHand = Stein(Farbe.Blau, Zahl.Fuenf)
        val steinNichtDa = Stein(Farbe.Rot, Zahl.Eins)

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine(steinHand),
            true
        )

        val tisch = Tisch(mutableListOf())

        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        val exception = assertThrows<InvalidObjectException> {
            spiel.anlegen(
                spieler = spieler,
                neuStein = steinNichtDa,
                kombi = 0,
                aktuellerTisch = tisch
            )
        }

        assertThat(exception.message).contains("Du hast diesen Stein nicht!")
    }

    @Test
    fun `anlegen - Stein wird aus Hand entfernt`() {
        val stein = Stein(Farbe.Rot, Zahl.Eins)

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine(stein),
            true
        )

        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        val neuesSpiel = spiel.anlegen(
            spieler = spieler,
            neuStein = stein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(neuesSpiel.aktivSpieler.brettReadOnly).doesNotContain(stein)
    }

    @Test
    fun `anlegen - Stein wird in Kombination eingefuegt`() {
        val stein = Stein(Farbe.Blau, Zahl.Sechs)

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spieler1 = Spieler(
            "Max",
            "1",
            create14Steine(stein),
            true
        )

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            tisch = tisch
        )

        spiel.anlegen(
            spieler = spieler1,
            neuStein = stein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(tisch.tischReadOnly[0].get()).contains(stein)
    }

    @Test
    fun `anlegen - Tisch anlegen wird ausgefuehrt`() {
        val stein = Stein(Farbe.Orange, Zahl.Zehn)

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Sieben),
                Stein(Farbe.Orange, Zahl.Acht),
                Stein(Farbe.Orange, Zahl.Neun)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine(stein),
            true

        )

        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        spiel.anlegen(
            spieler = spieler,
            neuStein = stein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(tisch.tischReadOnly.first().get()).contains(stein)
    }

    @Test
    fun `anlegen - neues Spiel wird erzeugt`() {
        val stein = Stein(Farbe.Schwarz, Zahl.Eins)

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Schwarz, Zahl.Zwei),
                Stein(Farbe.Schwarz, Zahl.Drei),
                Stein(Farbe.Schwarz, Zahl.Vier)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine(stein),
            true
        )

        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        val neuesSpiel = spiel.anlegen(
            spieler = spieler,
            neuStein = stein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(neuesSpiel).isNotSameAs(spiel)
    }

    @ParameterizedTest
    @MethodSource("steine")
    fun `anlegen - verschiedene Steine funktionieren korrekt`(stein: Stein) {
        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei)
            )
        )

        val tisch = Tisch(mutableListOf(kombi))

        val spieler = Spieler(
            "Maxi-Taxi",
            "1",
            create14Steine(stein),
            true
        )

        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        assertDoesNotThrow {
            spiel.anlegen(
                spieler = spieler,
                neuStein = stein,
                kombi = 0,
                aktuellerTisch = tisch
            )
        }
    }

    @ParameterizedTest
    @MethodSource("tmpListeAnlegenSzenarien")
    fun `tmpListe wird beim Anlegen korrekt gesetzt`(
        testfall: Pair<Stein, Kombinationen>
    ) {
        val neuStein = testfall.first
        val kombi = testfall.second

        val tisch = Tisch(mutableListOf(kombi))
        val spieler = Spieler(
            "Maxi-Taxi",
            "1",
            create14Steine(neuStein),
            true
        )

        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        spiel.anlegen(
            spieler = spieler,
            neuStein = neuStein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(tisch.tmpListe).isEmpty()
    }

    @ParameterizedTest
    @MethodSource("tmpListeAnlegenMitTmpSteinenSzenarien")
    fun `tmpListe wird beim Anlegen mit der vorhandenen tmpListe (Steinen) korrekt berechnet`(
        testfall: Triple<Stein, Kombinationen, List<Stein>>
    ) {
        val neuStein = testfall.first
        val kombi = testfall.second
        val tmpSteine = testfall.third

        val tisch = Tisch(mutableListOf(kombi))
        tisch.tmpListe = tmpSteine.toMutableList()

        val spieler = Spieler(
            "Maxi-Taxi",
            "1",
            create14Steine(neuStein),
            true
        )
        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = tisch
        )

        spiel.anlegen(
            spieler = spieler,
            neuStein = neuStein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(tisch.tmpListe).containsExactlyInAnyOrderElementsOf(tmpSteine)
    }

    @Test
    fun `anlegen mit Stein aus tmpListe aktualisiert tmpListe korrekt`() {
        val neuStein = Stein(Farbe.Orange, Zahl.Acht)
        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Fuenf),
                Stein(Farbe.Orange, Zahl.Sechs),
                Stein(Farbe.Orange, Zahl.Sieben)
            )
        )
        val tmpSteine = listOf(neuStein, Stein(Farbe.Rot, Zahl.Eins))

        val tisch = Tisch(mutableListOf(kombi))
        tisch.tmpListe = tmpSteine.toMutableList()

        val spieler = Spieler(
            "Maxi-Taxi",
            "1",
            create14Steine(),
            true
        )
        val spiel = Spiel(aktivSpieler = spieler, beutel = emptyList(), tisch = tisch)

        spiel.anlegen(
            spieler = spieler,
            neuStein = neuStein,
            kombi = 0,
            aktuellerTisch = tisch
        )

        assertThat(tisch.tmpListe).containsExactly(Stein(Farbe.Rot, Zahl.Eins))
    }
}