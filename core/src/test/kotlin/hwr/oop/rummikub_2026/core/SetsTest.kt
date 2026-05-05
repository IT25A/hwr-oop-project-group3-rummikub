package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SetsTest {

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        // given
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        )
        // when
        // then
        assertTrue(isValidGroup(set))
    }

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Sechs) und unterschiedliche Farben` () {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        // when
        // then
        assertTrue(isValidGroup(set))
    };

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben` () {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        // when
        // then
        assertTrue(isValidGroup(set))
    };
}