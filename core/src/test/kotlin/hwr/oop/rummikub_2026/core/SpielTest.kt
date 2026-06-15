package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import java.io.InvalidObjectException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse

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
        fun testKombis() = listOf(
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Zwei),
                    Stein(Farbe.Rot, Zahl.Drei)
                )
            ),
            Folge(
                mutableListOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben),
                    Stein(Farbe.Blau, Zahl.Acht)
                )
            ),
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
            ),
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
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Blau, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Eins)
                )
            )
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
    fun `Exception bei leerer Kombination`() {

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine()
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
                false,
                tisch
            )
        }
    }
    @Test
    fun `Kombination wird vom Tisch entfernt`() {

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine()
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
            false,
            tisch
        )

        assertFalse(
            neuesSpiel.tisch.tischReadOnly.contains(kombi)
        )
    }
    @Test
    fun `Steine der Kombination landen in tmpListe`() {

        val spieler = Spieler(
            "Max",
            "1",
            create14Steine()
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
            false,
            tisch
        )

        assertEquals(
            steine,
            tisch.tmpListe
        )
    }
//    @ParameterizedTest
//    @MethodSource("testKombis")
//    fun `Spieler loest Kombi auf, die existiert`(aufzuloesendeKombi : Kombinationen){
//        // given
//        val spieler = Spieler("Luxi-Taxi", "1", create14Steine())
//        val spiel = Spiel(
//            aktivSpieler = spieler,
//            beutel = emptyList(),
//            tisch = Tisch(mutableListOf(aufzuloesendeKombi))
//        )
//        val tischZuvor = spiel.tisch.tischReadOnly
//        //when
//        spiel.aufloesen(aufzuloesendeKombi)
//        //then
//        assertEquals(spiel.tisch.tmpListe, aufzuloesendeKombi.get().toList())
//        assertEquals(tischZuvor - spiel.tisch.tischReadOnly, aufzuloesendeKombi.get().toList())
//    }


    @ParameterizedTest
    @MethodSource("gueltigeZiehenSzenarien")
    fun `gueltiger Spieler zieht einen Stein aus dem Beutel`(
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
    fun `ungueltiger Spieler darf nicht ziehen wirft Exception`() {
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
    fun `Ziehen bei leerem Beutel wirft Exception`() {
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
    fun `ungueltiges auslegen wirft Exception`(
        testfall: Pair<Triple<List<Stein>, List<Stein>, Array<Stein>>, String>
    ) {
        // given
        val hand = testfall.first.first
        val neuStein = testfall.first.second
        val varargSteine = testfall.first.third

        val spieler = Spieler("Luxi-Taxi", "1", create14Steine(*hand.toTypedArray()))
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
    fun `auslegen von ungueltigem Spieler wirft Exception`() {
        // given
        val spieler1 = Spieler("Luxi-Taxi", "1", create14Steine(standardStein))
        val spieler2 = Spieler("Smilla", "2", create14Steine())
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
    fun `gueltiges auslegen einer neuen Kombination funktioniert`() {
        // given
        val spieler = Spieler("Luxi-Taxi", "1", create14Steine(standardStein, stein2, stein3))
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
}