package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import java.io.InvalidObjectException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.assertDoesNotThrow

class SpielTest {

    companion object {
        private val standardStein = Stein(Farbe.Blau, Zahl.Fuenf)
        private val steinRot = Stein(Farbe.Rot, Zahl.Eins)
        private val stein2 = Stein(Farbe.Blau, Zahl.Sechs)
        private val stein3 = Stein(Farbe.Blau, Zahl.Sieben)

        private fun create14Steine(vararg zusaetzlicheSteine: Stein): MutableList<Stein> {
            val steine = zusaetzlicheSteine.toMutableList()
            while (steine.size < 14) {
                steine.add(Stein(Farbe.Schwarz, Zahl.Eins))
            }
            return steine
        }
        @JvmStatic
        fun gueltigeSets3Steine() = listOf(
            Sets(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Blau, Zahl.Eins)
                )
            ),
            Sets(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sechs)
                )
            ),
            Sets(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Blau, Zahl.Dreizehn),
                    Stein(Farbe.Schwarz, Zahl.Dreizehn)
                )
            )
        )

        @JvmStatic
        fun gueltigeSets4Steine() = listOf(
            Sets(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Blau, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Eins)
                )
            ),
            Sets(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Sechs),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sechs)
                )
            ),
            Sets(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Dreizehn),
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Blau, Zahl.Dreizehn),
                    Stein(Farbe.Schwarz, Zahl.Dreizehn)
                )
            )
        )
        @JvmStatic
        fun ungueltigeKombi() = listOf(
            // Minimum
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Zwei),
                    Stein(Farbe.Rot, Zahl.Vier)
                )
            ),

            // Mittelfall
            Folge(
                mutableListOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben),
                    Stein(Farbe.Blau, Zahl.Acht)
                )
            ),

            // Maximum
            Folge(
                mutableListOf(
                    Stein(Farbe.Schwarz, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Zwei),
                    Stein(Farbe.Schwarz, Zahl.Drei),
                    Stein(Farbe.Schwarz, Zahl.Vier),
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Schwarz, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sieben),
                    Stein(Farbe.Schwarz, Zahl.Acht),
                    Stein(Farbe.Schwarz, Zahl.Neun),
                    Stein(Farbe.Schwarz, Zahl.Zehn),
                    Stein(Farbe.Schwarz, Zahl.Elf),
                    Stein(Farbe.Schwarz, Zahl.Zwoelf),
                    Stein(Farbe.Schwarz, Zahl.Eins)
                )
            )
        )
        @JvmStatic
        fun gueltigeFolgen() = listOf(
            // Minimum
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Zwei),
                    Stein(Farbe.Rot, Zahl.Drei)
                )
            ),

            // Mittelfall
            Folge(
                mutableListOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben),
                    Stein(Farbe.Blau, Zahl.Acht)
                )
            ),

            // Maximum
            Folge(
                mutableListOf(
                    Stein(Farbe.Schwarz, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Zwei),
                    Stein(Farbe.Schwarz, Zahl.Drei),
                    Stein(Farbe.Schwarz, Zahl.Vier),
                    Stein(Farbe.Schwarz, Zahl.Fuenf),
                    Stein(Farbe.Schwarz, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sieben),
                    Stein(Farbe.Schwarz, Zahl.Acht),
                    Stein(Farbe.Schwarz, Zahl.Neun),
                    Stein(Farbe.Schwarz, Zahl.Zehn),
                    Stein(Farbe.Schwarz, Zahl.Elf),
                    Stein(Farbe.Schwarz, Zahl.Zwoelf),
                    Stein(Farbe.Schwarz, Zahl.Dreizehn)
                )
            )
        )
        @JvmStatic
        fun steine() = listOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Sieben),
            Stein(Farbe.Schwarz, Zahl.Zwoelf),
            Stein(Farbe.Orange, Zahl.Dreizehn)
        )

        @JvmStatic
        fun gueltigeZiehenSzenarien() = listOf(
            Pair(listOf(steinRot), 15)
        )

        @JvmStatic
        fun ungueltigeAuslegenSzenarien() = listOf(
            Pair(
                Triple(listOf(standardStein), listOf(steinRot), arrayOf(steinRot)),
                "Du hast diesen Stein nicht!"
            ),
            Pair(
                Triple(listOf(standardStein), emptyList<Stein>(), arrayOf<Stein>()),
                "Keine Steine ausgewählt!"
            )
        )
    }
    @Test
    fun `aufloesen - Exception bei leerer Kombination`() {

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine(),
            true
        )
        val leereKombi = Folge(
            mutableListOf()
        )
        val tisch = Tisch(
            mutableListOf(leereKombi)
        )
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
            create14Steine(),
            true
        )

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        val tisch = Tisch(
            mutableListOf(kombi)
        )

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

        assertFalse(
            neuesSpiel.tisch.tischReadOnly.contains(kombi)
        )
    }
    @Test
    fun `aufloesen - Steine der Kombination landen in tmpListe`() {

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine(),
            true
        )

        val steine = mutableListOf(
            Stein(Farbe.Orange, Zahl.Zehn),
            Stein(Farbe.Orange, Zahl.Elf),
            Stein(Farbe.Orange, Zahl.Zwoelf)
        )

        val kombi = Folge(steine)

        val tisch = Tisch(
            mutableListOf(kombi)
        )

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
            create14Steine(),
            true
        )

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun)
            )
        )

        val tisch = Tisch(
            mutableListOf(kombi)
        )

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
            create14Steine(),
            true
        )

        val spieler2 = Spieler(
            "Moritz",
            "2",
            create14Steine(),
            true
        )

        val kombi = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun)
            )
        )

        val tisch = Tisch(
            mutableListOf(kombi)
        )

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

        assertThat(exception.message)
            .contains("Spieler ist nicht an der Reihe!")
    }


    @ParameterizedTest
    @MethodSource("gueltigeZiehenSzenarien")
    fun `ziehen - gueltiger Spieler zieht einen Stein aus dem Beutel`(
        testfall: Pair<List<Stein>, Int>
    ) {
        // given
        val spieler = Spieler("Luxi-Taxi", "1", create14Steine())
        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = testfall.first,
            tisch = Tisch(mutableListOf())
        )

        // when
        val neuesSpiel = spiel.ziehen(spieler)

        // then
        assertThat(neuesSpiel.aktivSpieler.boardReadOnly)
            .hasSize(testfall.second)

        assertThat(neuesSpiel.aktivSpieler.boardReadOnly)
            .contains(testfall.first.first())

        assertThat(neuesSpiel.beutel)
            .isEmpty()
    }

    @Test
    fun `ziehen - ungueltiger Spieler darf nicht ziehen wirft Exception`() {
        // given
        val spieler1 = Spieler("Luxi-Taxi", "1", create14Steine())
        val spieler2 = Spieler("Smilla", "2", create14Steine())
        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = listOf(steinRot),
            tisch = Tisch(mutableListOf())
        )

        // when & then
        val exception = assertThrows<InvalidObjectException> {
            spiel.ziehen(spieler2)
        }

        assertThat(exception.message)
            .contains("Spieler ist nicht an der Reihe!")
    }

    @Test
    fun `ziehen - Ziehen bei leerem Beutel wirft Exception`() {
        // given
        val spieler = Spieler("Luxi-Taxi", "1", create14Steine())
        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = Tisch(mutableListOf())
        )

        // when & then
        val exception = assertThrows<IllegalStateException> {
            spiel.ziehen(spieler)
        }

        assertThat(exception.message)
            .contains("Der Beutel ist leer!")
    }

    @ParameterizedTest
    @MethodSource("ungueltigeAuslegenSzenarien")
    fun `auslegen - ungueltiges auslegen wirft Exception`(
        testfall: Pair<Triple<List<Stein>, List<Stein>, Array<Stein>>, String>
    ) {
        // given
        val hand = testfall.first.first
        val neuStein = testfall.first.second
        val varargSteine = testfall.first.third

        val spieler = Spieler("Luxi-Taxi", "1", create14Steine(*hand.toTypedArray()), true)
        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = Tisch(mutableListOf())
        )

        // when & then
        val exception = assertThrows<InvalidObjectException> {
            spiel.auslegen(
                spieler = spieler,
                istSet = false,
                aktuellerTisch = spiel.tisch,
                steine = *varargSteine
            )
        }

        assertThat(exception.message)
            .contains(testfall.second)
    }

    @Test
    fun `auslegen - auslegen von ungueltigem Spieler wirft Exception`() {
        // given
        val spieler1 = Spieler("Luxi-Taxi", "1", create14Steine(standardStein), true)
        val spieler2 = Spieler("Smilla", "2", create14Steine(), true)
        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            tisch = Tisch(mutableListOf())
        )

        // when & then
        val exception = assertThrows<InvalidObjectException> {
            spiel.auslegen(
                spieler = spieler2,
                istSet = false,
                aktuellerTisch = spiel.tisch,
                steine = arrayOf(standardStein)
            )
        }

        assertThat(exception.message)
            .contains("Spieler ist nicht an der Reihe!")
    }

    @Test
    fun `auslegen - gueltiges auslegen einer neuen Kombination funktioniert`() {
        // given
        val spieler = Spieler("Luxi-Taxi", "1", create14Steine(standardStein, stein2, stein3), true)
        val spiel = Spiel(
            aktivSpieler = spieler,
            beutel = emptyList(),
            tisch = Tisch(mutableListOf())
        )

        // when
        val neuesSpiel = spiel.auslegen(
            spieler = spieler,
            istSet = false,
            aktuellerTisch = spiel.tisch,
            steine = arrayOf(standardStein, stein2, stein3)
        )

        // then
        assertThat(neuesSpiel.aktivSpieler.boardReadOnly)
            .hasSize(11)
    }
    @Test
    fun `anlegen - ungueltiger Spieler wirft Exception`() {

        val spieler1 = Spieler("Max", "1", create14Steine(),true)
        val spieler2 = Spieler("Moritz", "2", create14Steine(),true)

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

        assertThat(exception.message)
            .contains("Spieler ist nicht an der Reihe!")
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

        val tisch = Tisch(
            mutableListOf()
        )

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

        assertThat(exception.message)
            .contains("Du hast diesen Stein nicht!")
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

        assertThat(neuesSpiel.aktivSpieler.boardReadOnly)
            .doesNotContain(stein)
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

        // je nach Implementierung von anlegen()
        assertThat(tisch.tischReadOnly[0].get())
            .contains(stein)
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

        // Kombination sollte verändert worden sein
        assertThat(tisch.tischReadOnly.first().get())
            .contains(stein)
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

        assertThat(neuesSpiel)
            .isNotSameAs(spiel)
    }

    @ParameterizedTest
    @MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
    fun `Sets und Folgen sind gueltig im Tisch`(
        kombi: Kombinationen
    ) {
        val spiel = Spiel(
            aktivSpieler = Spieler("spieler1", "1", create14Steine(), true),
            beutel = emptyList(),
            tisch = Tisch(mutableListOf(kombi))

        )

        Assertions.assertDoesNotThrow {
            spiel.gueltigerZug()
        }
    }

    @ParameterizedTest
    @MethodSource("ungueltigeKombi")
    fun `Sets und Folgen sind nicht gueltig im Tisch`(
        kombi: Kombinationen
    ) {

        val spiel = Spiel(
            aktivSpieler = Spieler("spieler1", "1", create14Steine(),  true),
            beutel = emptyList(),
            tisch = Tisch(mutableListOf(kombi))

        )
        assertThrows<IllegalArgumentException> {
            spiel.gueltigerZug()
        }
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

        assertDoesNotThrow {
            spiel.anlegen(
                spieler = spieler,
                neuStein = stein,
                kombi = 0,
                aktuellerTisch = tisch
            )
        }
    }
}