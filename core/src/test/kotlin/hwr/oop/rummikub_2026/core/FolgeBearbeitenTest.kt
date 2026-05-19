package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class FolgeBearbeitenTest {

    @Test
    fun `Hinten hinzufuegen funktioniert`() {
        // give
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // then
        assertDoesNotThrow {
            folge.hinzufuegenHinten(Stein(Farbe.Rot, Zahl.Fuenf))
        }
        assertThat(Stein(Farbe.Rot, Zahl.Fuenf) in folge.folgeReadOnly)
    }

    @Test
    fun `Hinten hinzufuegen mit falscher Farbe`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenHinten(Stein(Farbe.Blau, Zahl.Fuenf))
        }

        // then
        assertThat(exception.message)
            .contains("Alle Steine muessen die selbe Farbe haben")
        assertThat(Stein(Farbe.Blau, Zahl.Fuenf) !in folge.folgeReadOnly)
    }

    @Test
    fun `Hinten hinzufuegen mit falscher Reihenfolge`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenHinten(Stein(Farbe.Rot, Zahl.Sechs))
        }

        // then
        assertThat(exception.message)
            .contains("Steine muessen aufeinander Folgen")
        assertThat(Stein(Farbe.Rot, Zahl.Sechs) !in folge.folgeReadOnly)
    }

    @Test
    fun `Hinten darf nicht vorne eingefuegt werden`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenHinten(Stein(Farbe.Rot, Zahl.Eins))
        }

        // then
        assertThat(exception.message)
            .contains("Steine muessen aufeinander Folgen")
        assertThat(Stein(Farbe.Rot, Zahl.Eins) !in folge.folgeReadOnly)
    }

    @Test
    fun `Vorne hinzufuegen funktioniert`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // then
        assertDoesNotThrow {
            folge.hinzufuegenVorne(Stein(Farbe.Rot, Zahl.Eins))
        }
        assertThat(Stein(Farbe.Rot, Zahl.Eins) in folge.folgeReadOnly)
    }

    @Test
    fun `Vorne hinzufuegen mit falscher Farbe`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenVorne(Stein(Farbe.Blau, Zahl.Eins))
        }

        // then
        assertThat(exception.message)
            .contains("Alle Steine muessen die selbe Farbe haben")
        assertThat(Stein(Farbe.Blau, Zahl.Eins) !in folge.folgeReadOnly)
    }

    @Test
    fun `Vorne hinzufuegen mit falscher Reihenfolge`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenVorne(Stein(Farbe.Rot, Zahl.Sechs))
        }

        // then
        assertThat(exception.message)
            .contains("Steine muessen aufeinander Folgen")
        assertThat(Stein(Farbe.Rot, Zahl.Sechs) !in folge.folgeReadOnly)
    }

    @Test
    fun `Vorne darf nicht hinten eingefuegt werden`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenVorne(Stein(Farbe.Rot, Zahl.Fuenf))
        }

        // then
        assertThat(exception.message)
            .contains("Steine muessen aufeinander Folgen")
        assertThat(Stein(Farbe.Rot, Zahl.Fuenf) !in folge.folgeReadOnly)
    }


    @Test
    fun `Hinten entfernen funktioniert`() {
        // give
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )
        //when
        val stein = folge.wegnehmenHinten()
        // then

        assertThat(Stein(Farbe.Rot, Zahl.Fuenf) !in folge.folgeReadOnly)
        assertThat(stein == (Stein(Farbe.Rot, Zahl.Fuenf)))
    }

    @Test
    fun `Vorne entfernen funktioniert`() {
        // give
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )
        //when
        val stein = folge.wegnehmenVorne()
        // then

        assertThat(Stein(Farbe.Rot, Zahl.Zwei) !in folge.folgeReadOnly)
        assertThat(stein == (Stein(Farbe.Rot, Zahl.Zwei)))
    }
}