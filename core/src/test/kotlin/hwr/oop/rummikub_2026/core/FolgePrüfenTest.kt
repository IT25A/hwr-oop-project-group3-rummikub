package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class FolgePrüfenTest {
    @Test
    fun `Folge mit genau 3 Steinen ist valide (Minimum)`() {
        // given
        val steine = listOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Zwei),
            Stein(Farbe.Rot, Zahl.Drei)
        )

        // when
        val folge = Folge(steine)

        // then - sollte keine Exception werfen
        folge.isValid()
        assertThat(folge.FolgeListe.size).isEqualTo(3)
    }

    @Test
    fun `Folge mit 13 Steinen ist valide (Maximum)`() {
        // given
        val steine = listOf(
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
            Stein(Farbe.Blau, Zahl.Dreizehn)
        )

        // when
        val folge = Folge(steine)

        // then - sollte keine Exception werfen
        folge.isValid()
        assertThat(folge.FolgeListe.size).isEqualTo(13)
    }

    @Test
    fun `Folge mit weniger als 3 Steinen ist nicht valide`() {
        // given
        val steine = listOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Orange, Zahl.Zwei)
        )

        // when
        val folge = Folge(steine)

        // then
        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }

    @Test
    fun `Folge mit mehr als 13 Steinen ist nicht valide`() {
        // given
        val steine = listOf(
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
            Stein(Farbe.Schwarz, Zahl.Dreizehn),

            Stein(Farbe.Schwarz, Zahl.Eins)
        )

        // when
        val folge = Folge(steine)

        // then
        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }
        assertThat(exception.message).contains("Maximal 13 Steine")
    }

    //FarbTests

    @Test
    fun `Alle Steine selbe Farbe`() {
        // given
        val steine = listOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Zwei),
            Stein(Farbe.Rot, Zahl.Drei)
        )

        //when
        val folge = Folge(steine)

        //then
        assertDoesNotThrow { folge.isValid() }
    }

    @Test

    fun `Steine haben nicht die selbe Farbe`() {
        // given
        val steine = listOf(
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Zwei),
            Stein(Farbe.Rot, Zahl.Drei)
        )

        //when
        val folge = Folge(steine)

        // then
        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }
        assertThat(exception.message).contains("Alle Steine müssen die selbe Farbe haben")

    }

    //Indexverschiebungs-Tests

    @Test
    fun `Zahlen folgen korrekt aufeinander`() {
        // given
        val steine = listOf(
            Stein(Farbe.Orange, Zahl.Fuenf),
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Orange, Zahl.Sieben),
            Stein(Farbe.Orange, Zahl.Acht)
        )

        //when
        val folge = Folge(steine)

        //then
        assertDoesNotThrow { folge.isValid() }
    }

    @Test
    fun `Zahlen folgen nicht aufeinander`() {
        // given
        val steine = listOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Zwei),
            Stein(Farbe.Rot, Zahl.Vier),
        )

        //when
        val folge = Folge(steine)

        // then
        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }
        assertThat(exception.message).contains("Steine müssen aufeinander Folgen")
    }

    @Test
    fun `Zahlen sind nicht in der richtigen Reihenfolge`() {
        // given - 3, 1, 2 (unsortiert)
        val steine = listOf(
            Stein(Farbe.Blau, Zahl.Drei),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Zwei)
        )

        //when
        val folge = Folge(steine)

        // then
        val exception = assertThrows<IllegalArgumentException> {
            folge.isValid()
        }
        assertThat(exception.message).contains("Steine müssen aufeinander Folgen")
    }
}