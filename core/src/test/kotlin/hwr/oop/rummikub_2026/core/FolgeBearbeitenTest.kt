package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class FolgeBearbeitenTest {

    companion object {

        private fun standardFolge() = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        )

        @JvmStatic
        fun gueltigeHintenSteine() = listOf(
            Stein(Farbe.Rot, Zahl.Fuenf)
        )

        @JvmStatic
        fun gueltigeVorneSteine() = listOf(
            Stein(Farbe.Rot, Zahl.Eins)
        )

        @JvmStatic
        fun ungueltigeHintenSteine() = listOf(

            // falsche Farbe
            Pair(
                Stein(Farbe.Blau, Zahl.Fuenf),
                "Alle Steine muessen die selbe Farbe haben"
            ),

            // Lücke
            Pair(
                Stein(Farbe.Rot, Zahl.Sechs),
                "Steine muessen aufeinander Folgen"
            ),

            // eigentlich vorne
            Pair(
                Stein(Farbe.Rot, Zahl.Eins),
                "Steine muessen aufeinander Folgen"
            ),

            // doppelte Zahl
            Pair(
                Stein(Farbe.Rot, Zahl.Vier),
                "Steine muessen aufeinander Folgen"
            )
        )

        @JvmStatic
        fun ungueltigeVorneSteine() = listOf(

            // falsche Farbe
            Pair(
                Stein(Farbe.Blau, Zahl.Eins),
                "Alle Steine muessen die selbe Farbe haben"
            ),

            // falsche Reihenfolge
            Pair(
                Stein(Farbe.Rot, Zahl.Sechs),
                "Steine muessen aufeinander Folgen"
            ),

            // eigentlich hinten
            Pair(
                Stein(Farbe.Rot, Zahl.Fuenf),
                "Steine muessen aufeinander Folgen"
            ),

            // doppelte Zahl
            Pair(
                Stein(Farbe.Rot, Zahl.Zwei),
                "Steine muessen aufeinander Folgen"
            )
        )
    }

    @ParameterizedTest
    @MethodSource("gueltigeHintenSteine")
    fun `gueltiges hinten hinzufuegen funktioniert`(
        stein: Stein
    ) {
        // given
        val folge = standardFolge()

        // then
        assertDoesNotThrow {
            folge.hinzufuegenHinten(stein)
        }

        assertThat(folge.folgeReadOnly)
            .contains(stein)
    }

    @ParameterizedTest
    @MethodSource("ungueltigeHintenSteine")
    fun `ungueltiges hinten hinzufuegen wirft exception`(
        testfall: Pair<Stein, String>
    ) {
        // given
        val folge = standardFolge()

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenHinten(testfall.first)
        }

        // then
        assertThat(exception.message)
            .contains(testfall.second)
    }

    @ParameterizedTest
    @MethodSource("gueltigeVorneSteine")
    fun `gueltiges vorne hinzufuegen funktioniert`(
        stein: Stein
    ) {
        // given
        val folge = standardFolge()

        // then
        assertDoesNotThrow {
            folge.hinzufuegenVorne(stein)
        }

        assertThat(folge.folgeReadOnly)
            .contains(stein)
    }

    @ParameterizedTest
    @MethodSource("ungueltigeVorneSteine")
    fun `ungueltiges vorne hinzufuegen wirft exception`(
        testfall: Pair<Stein, String>
    ) {
        // given
        val folge = standardFolge()

        // when
        val exception = assertThrows<IllegalArgumentException> {
            folge.hinzufuegenVorne(testfall.first)
        }

        // then
        assertThat(exception.message)
            .contains(testfall.second)
    }

    @Test
    fun `wegnehmen hinten entfernt letzten Stein`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        // when
        val entfernt = folge.wegnehmenHinten()

        // then
        assertThat(entfernt)
            .isEqualTo(Stein(Farbe.Rot, Zahl.Fuenf))

        assertThat(folge.folgeReadOnly)
            .doesNotContain(Stein(Farbe.Rot, Zahl.Fuenf))

        assertThat(folge.folgeReadOnly)
            .hasSize(3)
    }

    @Test
    fun `wegnehmen vorne entfernt ersten Stein `() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        // when
        val entfernt = folge.wegnehmenVorne()

        // then
        assertThat(entfernt)
            .isEqualTo(Stein(Farbe.Rot, Zahl.Zwei))

        assertThat(folge.folgeReadOnly)
            .doesNotContain(Stein(Farbe.Rot, Zahl.Zwei))

        assertThat(folge.folgeReadOnly)
            .hasSize(3)
    }
}