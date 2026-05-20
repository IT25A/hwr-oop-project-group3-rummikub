package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class FolgePruefenTest {

    companion object {

        @JvmStatic
        fun gueltigeFolgen() = listOf(
            // Minimum
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei)
            ),

            // Mittelfall
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht)
            ),

            // Maximum
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

        @JvmStatic
        fun ungueltigeLaengen() = listOf(
            // Zu kurz
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei)
            ),

            // Zu lang
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Eins),
                Stein(Farbe.Blau, Zahl.Zwei),
                Stein(Farbe.Blau, Zahl.Drei),
                Stein(Farbe.Blau, Zahl.Vier),
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun),
                Stein(Farbe.Blau, Zahl.Zehn),
                Stein(Farbe.Blau, Zahl.Elf),
                Stein(Farbe.Blau, Zahl.Zwoelf),
                Stein(Farbe.Blau, Zahl.Dreizehn),
                Stein(Farbe.Blau, Zahl.Eins)
            )
        )

        @JvmStatic
        fun ungueltigeFarben() = listOf(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Blau, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei)
            ),

            mutableListOf(
                Stein(Farbe.Orange, Zahl.Fuenf),
                Stein(Farbe.Orange, Zahl.Sechs),
                Stein(Farbe.Schwarz, Zahl.Sieben)
            )
        )

        @JvmStatic
        fun ungueltigeReihenfolgen() = listOf(

            // Lücke
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Vier)
            ),

            // Komplett unsortiert
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Drei),
                Stein(Farbe.Blau, Zahl.Eins),
                Stein(Farbe.Blau, Zahl.Zwei)
            ),

            // Rückwärts
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Sieben),
                Stein(Farbe.Orange, Zahl.Sechs),
                Stein(Farbe.Orange, Zahl.Fuenf)
            )
        )
    }

    @Test
    fun `folgeReadOnly liefert die originale Liste`() {
        // given
        val steine = mutableListOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Zwei),
            Stein(Farbe.Rot, Zahl.Drei)
        )

        // when
        val folge = Folge(steine)

        // then
        assertThat(folge.folgeReadOnly).isEqualTo(steine)
    }

    @ParameterizedTest
    @MethodSource("gueltigeFolgen")
    fun `gueltige Folgen werfen keine Exception`(
        steine: MutableList<Stein>
    ) {
        val folge = Folge(steine)

        assertDoesNotThrow {
            folge.isValid()
        }
    }

    @ParameterizedTest
    @MethodSource("ungueltigeLaengen")
    fun `ungueltige Laengen werfen Exception`(
        steine: MutableList<Stein>
    ) {
        val folge = Folge(steine)

        assertThrows<IllegalArgumentException> {
            folge.isValid()
        }
    }

    @ParameterizedTest
    @MethodSource("ungueltigeFarben")
    fun `unterschiedliche Farben werfen Exception`(
        steine: MutableList<Stein>
    ) {
        val folge = Folge(steine)

        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }

        assertThat(exception.message)
            .contains("Alle Steine muessen die selbe Farbe haben")
    }

    @ParameterizedTest
    @MethodSource("ungueltigeReihenfolgen")
    fun `ungueltige Reihenfolgen werfen Exception`(
        steine: MutableList<Stein>
    ) {
        val folge = Folge(steine)

        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }

        assertThat(exception.message)
            .contains("Steine muessen aufeinander Folgen")
    }
}