package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

//import org.junit.jupiter.api.Assertions.assertTrue


class SetsPruefenTest {
    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        )
        //when
        val sets = Sets(set)

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `ungueltige Set mit vier Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //when
        val sets = Sets(set)
        sets.istGueltig()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `ungueltige Set mit vier Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)
        sets.istGueltig()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `ungueltige Set mit vier Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `ungueltige Set mit vier Steine, unterschiedlichen Zahl und unterschiedlichen Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen dieselbe Zahl haben")
    }

    @Test
    fun `gueltige Set mit drei Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
        )
        //when
        val sets = Sets(set)
        sets.istGueltig()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `ungueltige Set mit drei Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `gueltige Set mit drei Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //when
        val sets = Sets(set)
        sets.istGueltig()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `ungueltige Set mit drei Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `gueltige Set mit drei Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)
        sets.istGueltig()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `ungueltige Set mit drei Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen unterschiedliche Farbe haben")
    }

    @Test
    fun `ungueltige Set mit drei Steine, unterschiedlichen Zahl und unterschiedliche Farben`() {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltig()
        }
        assertThat(exception.message).contains("Alle Steine muessen dieselbe Zahl haben")
    }
}