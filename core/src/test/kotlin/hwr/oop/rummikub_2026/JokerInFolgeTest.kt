package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.assertj.core.api.Assertions.assertThat

class FolgeJokerTest {

    private fun joker() = Stein(Farbe.Joker, Zahl.Eins)

    @Test
    fun `Folge mit Joker am Anfang ist gueltig`() {
        val folge = Folge(
            mutableListOf(
                joker(),
                Stein(Farbe.Rot, Zahl.Sechs),
                Stein(Farbe.Rot, Zahl.Sieben)
            )
        )

        folge.istGueltig()

        assertThat(folge.folgeReadOnly[0].zahl())
            .isEqualTo(Zahl.Fuenf)
    }


    @Test
    fun `Folge mit zwei Jokern am Anfang ist gueltig`() {
        val folge = Folge(
            mutableListOf(
                joker(),
                joker(),
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
    fun `Joker in der Mitte ersetzt fehlende Zahl`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Fuenf),
                joker(),
                Stein(Farbe.Rot, Zahl.Sieben)
            )
        )

        folge.istGueltig()

        assertThat(folge.folgeReadOnly[1].zahl())
            .isEqualTo(Zahl.Sechs)
    }


    @Test
    fun `Joker am Ende ersetzt naechste Zahl`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                joker()
            )
        )

        folge.istGueltig()

        assertThat(folge.folgeReadOnly[2].zahl())
            .isEqualTo(Zahl.Sieben)
    }


    @Test
    fun `Joker kann eine Luecke nicht von mehreren Zahlen schliessen`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Fuenf),
                joker(),
                Stein(Farbe.Rot, Zahl.Zehn)
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Joker vor Eins ist ungueltig`() {
        val folge = Folge(
            mutableListOf(
                joker(),
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei)
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Joker nach Dreizehn ist ungueltig`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwoelf),
                Stein(Farbe.Rot, Zahl.Dreizehn),
                joker()
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Joker mit falscher Farbe verletzt nicht Farbregel`() {
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                joker()
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Nur Joker sind keine gueltige Folge`() {
        val folge = Folge(
            mutableListOf(
                joker(),
                joker(),
                joker()
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }


    @Test
    fun `Ein einzelner Joker kann keinen Wert bekommen`() {
        val folge = Folge(
            mutableListOf(
                joker()
            )
        )

        assertThrows<IllegalArgumentException> {
            folge.istGueltig()
        }
    }
}