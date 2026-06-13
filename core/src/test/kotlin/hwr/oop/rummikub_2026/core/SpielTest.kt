package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import java.io.InvalidObjectException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Test

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