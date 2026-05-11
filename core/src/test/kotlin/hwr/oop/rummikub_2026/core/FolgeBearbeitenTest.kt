package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class FolgeBearbeitenTest {

    @Test
    fun `Hinten hinzufügen funktioniert`() {
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
            folge.hinzufuegenHinten(Stein(Farbe.Rot, Zahl.Fuenf))
        }
    }

    @Test
    fun `Hinten hinzufügen mit falscher Farbe`() {
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
            .contains("Alle Steine müssen die selbe Farbe haben")
    }

    @Test
    fun `Hinten hinzufügen mit falscher Reihenfolge`() {
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
            .contains("Steine müssen aufeinander Folgen")
    }

    @Test
    fun `Hinten darf nicht vorne eingefügt werden`() {
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
            .contains("Steine müssen aufeinander Folgen")
    }

    @Test
    fun `Vorne hinzufügen funktioniert`() {
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
    }

    @Test
    fun `Vorne hinzufügen mit falscher Farbe`() {
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
            .contains("Alle Steine müssen die selbe Farbe haben")
    }

    @Test
    fun `Vorne hinzufügen mit falscher Reihenfolge`() {
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
            .contains("Steine müssen aufeinander Folgen")
    }

    @Test
    fun `Vorne darf nicht hinten eingefügt werden`() {
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
            .contains("Steine müssen aufeinander Folgen")
    }
}