package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.assertj.core.api.Assertions.assertThat

class JokerWertCoverageTest {


    @Test
    fun `Joker vorne bekommt Wert vor erstem normalen Stein`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Joker, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Sechs),
                Stein(Farbe.Rot, Zahl.Sieben)
            )
        )

        folge.istGueltig()

        assertThat(folge.folgeReadOnly[0].zahl())
            .isEqualTo(Zahl.Fuenf)
    }


    @Test
    fun `Zwei Joker vorne werden rekursiv berechnet`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Joker, Zahl.Eins),
                Stein(Farbe.Joker, Zahl.Eins),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht)
            )
        )

        folge.istGueltig()

        assertThat(folge.folgeReadOnly[0].zahl())
            .isEqualTo(Zahl.Fuenf)

        assertThat(folge.folgeReadOnly[1].zahl())
            .isEqualTo(Zahl.Sechs)
    }


    @Test
    fun `Kein Joker am Anfang gibt normale Zahl zurueck`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Fuenf),
                Stein(Farbe.Rot, Zahl.Sechs),
                Stein(Farbe.Rot, Zahl.Sieben)
            )
        )

        folge.istGueltig()

        assertThat(folge.folgeReadOnly[0].zahl())
            .isEqualTo(Zahl.Fuenf)
    }


    @Test
    fun `Ein einzelner Joker wirft Exception`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Joker, Zahl.Eins)
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Leere Liste wird abgelehnt`() {
        val folge = Folge(
            mutableListOf()
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Joker kann keinen Wert kleiner als Eins bekommen`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Joker, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei)
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Joker nach Dreizehn kann keinen Wert bekommen`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwoelf),
                Stein(Farbe.Rot, Zahl.Dreizehn),
                Stein(Farbe.Joker, Zahl.Eins)
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }
}