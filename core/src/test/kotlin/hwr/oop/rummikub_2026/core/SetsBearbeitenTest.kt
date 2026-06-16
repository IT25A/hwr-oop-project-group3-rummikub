package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SetsBearbeitenTest {

    data class SetTestfall(
        val set: MutableList<Stein>,
        val stein: Stein,
        val erwarteteFehlerNachricht: String
    )

    companion object {
        @JvmStatic
        fun gueltigeHinzufuegenZu3erSet() = listOf(
            Pair(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Blau, Zahl.Eins)
                ),
                Stein(Farbe.Schwarz, Zahl.Eins)
            ),
            Pair(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Sechs),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sechs)
                ),
                Stein(Farbe.Blau, Zahl.Sechs)
            ),
            Pair(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Dreizehn),
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Blau, Zahl.Dreizehn)
                ),
                Stein(Farbe.Schwarz, Zahl.Dreizehn)
            )
        )

        @JvmStatic
        fun ungueltigeHinzufuegenZu4erSet() = listOf(
            SetTestfall(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Blau, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Eins)
                ),
                Stein(Farbe.Schwarz, Zahl.Eins),
                "Maximal 4 Steine"
            ),
            SetTestfall(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Sechs),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sechs)
                ),
                Stein(Farbe.Schwarz, Zahl.Sechs),
                "Maximal 4 Steine"
            ),
            SetTestfall(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Dreizehn),
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Blau, Zahl.Dreizehn),
                    Stein(Farbe.Schwarz, Zahl.Dreizehn)
                ),
                Stein(Farbe.Schwarz, Zahl.Sechs),
                "Maximal 4 Steine"
            )
        )

        @JvmStatic
        fun gueltigesWegnehmenVon4erSet() = listOf(
            Pair(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Blau, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Eins)
                ),
                Stein(Farbe.Rot, Zahl.Eins)
            ),
            Pair(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Sechs),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Schwarz, Zahl.Sechs)
                ),
                Stein(Farbe.Blau, Zahl.Sechs)
            ),
            Pair(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Dreizehn),
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Blau, Zahl.Dreizehn),
                    Stein(Farbe.Schwarz, Zahl.Dreizehn)
                ),
                Stein(Farbe.Orange, Zahl.Dreizehn)
            )
        )

        @JvmStatic
        fun ungueltigesWegnehmenVon3erSet() = listOf(
            SetTestfall(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Schwarz, Zahl.Eins)
                ),
                Stein(Farbe.Orange, Zahl.Eins),
                "Mindestens 3 Steine"
            ),
            SetTestfall(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Sechs),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sechs)
                ),
                Stein(Farbe.Rot, Zahl.Sechs),
                "Mindestens 3 Steine"
            ),
            SetTestfall(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Dreizehn),
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Schwarz, Zahl.Dreizehn)
                ),
                Stein(Farbe.Schwarz, Zahl.Dreizehn),
                "Mindestens 3 Steine"
            )
        )
    }

    @ParameterizedTest
    @MethodSource("gueltigeHinzufuegenZu3erSet")
    fun `Hinzufuegen zu 3er Set = gueltiges 4er Set`(
        testfall: Pair<MutableList<Stein>, Stein>
    ) {
        val set = Sets(testfall.first)

        assertDoesNotThrow {
            set.hinzufuegenZumSet(testfall.second)
        }
        assertThat(testfall.second in set.setReadOnly).isTrue
    }

    @ParameterizedTest
    @MethodSource("ungueltigeHinzufuegenZu4erSet")
    fun `Hinzufuegen zu 4er Set = ungueltiges Set`(
        testfall: SetTestfall
    ) {
        val set = Sets(testfall.set)

        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(testfall.stein)
            set.istGueltig()
        }
        assertThat(exception.message).contains(testfall.erwarteteFehlerNachricht)
    }

    @ParameterizedTest
    @MethodSource("gueltigesWegnehmenVon4erSet")
    fun `Wegnehmen vom 4er Set = gueltiges 3er Set`(
        testfall: Pair<MutableList<Stein>, Stein>
    ) {
        val sets = Sets(testfall.first)

        sets.wegnehmenVomSet(testfall.second)
        sets.istGueltig()

        assertThat(sets.setReadOnly).doesNotContain(testfall.second)
    }

    @ParameterizedTest
    @MethodSource("ungueltigesWegnehmenVon3erSet")
    fun `Wegnehmen vom 3er Set = ungueltiges Set`(
        testfall: SetTestfall
    ) {
        val sets = Sets(testfall.set)

        val exception = assertThrows<IllegalArgumentException> {
            sets.wegnehmenVomSet(testfall.stein)
            sets.istGueltig()
        }
        
        assertThat(sets.setReadOnly).doesNotContain(testfall.stein)
        assertThat(exception.message).contains(testfall.erwarteteFehlerNachricht)
    }

    @Test
    fun `hinzufuegenZumSet exception bei zu vielen Steinen`() {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(Stein(Farbe.Orange, Zahl.Eins))
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `hinzufuegenZumSet exception bei falscher Zahl`() {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Drei),
            Stein(Farbe.Rot, Zahl.Drei),
            Stein(Farbe.Blau, Zahl.Drei)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(Stein(Farbe.Schwarz, Zahl.Vier))
        }
        assertThat(exception.message).contains("Alle Steine muessen dieselbe Zahl haben")
    }

    @Test
    fun `hinzufuegenZumSet exception bei doppelter Farbe`() {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Fuenf),
            Stein(Farbe.Rot, Zahl.Fuenf),
            Stein(Farbe.Blau, Zahl.Fuenf)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(Stein(Farbe.Rot, Zahl.Fuenf))
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `wegnehmenVomSet exception bei zu wenig Steinen`() {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Sieben),
            Stein(Farbe.Rot, Zahl.Sieben),
            Stein(Farbe.Blau, Zahl.Sieben)
        ))

        val exception = assertThrows<IllegalArgumentException> {
            set.wegnehmenVomSet(Stein(Farbe.Orange, Zahl.Sieben))
        }
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }
}