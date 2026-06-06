package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TischTest {
    companion object {
        @JvmStatic
        fun gueltigeSteineFuerFolgen() = listOf(
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
    }

    @ParameterizedTest
    @MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
    fun `Folgen und Sets koennen auf dem Tisch liegen`(
        kombi: Kombinationen
    ) {
        val testTisch = Tisch(mutableListOf(kombi))

        assertThat(testTisch.tischReadOnly).contains(kombi)
    }

    @ParameterizedTest
    @MethodSource("gueltigeSteineFuerFolgen")
    fun `kombi legen fuer Folge pruefen`(
        liste: MutableList<Stein>
    ) {
        val tisch = Tisch(mutableListOf())
        val test = tisch.kombiLegen(false, *liste.toTypedArray())
        //*list.toIntArray()

        assertThat(tisch.tischReadOnly).contains(test)
    }
}