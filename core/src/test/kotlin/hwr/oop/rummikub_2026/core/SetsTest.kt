package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

//import org.junit.jupiter.api.Assertions.assertTrue


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
        //when
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isTrue()
    }

    @Test
    fun `ungueltige Set mit vier Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        )
        //then
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isFalse()
    }

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        // when
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)
        //then
        assertThat(istGueltig).isTrue()
    }

    @Test
    fun `ungueltige Set mit vier Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //then
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isFalse()
    }

    @Test
    fun `gueltige Set mit vier Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        // when
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)
        //then
        assertThat(istGueltig).isTrue()
    }

    @Test
    fun `ungueltige Set mit vier Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        //then
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isFalse()
    }

    @Test
    fun `gueltige Set mit drei Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        // given
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
        )
        // when
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)
        //then
        assertThat(istGueltig).isTrue()
    }

    @Test
    fun `ungueltige Set mit drei Steine, selbe Zahl (Eins) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
        )
        //then
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isFalse()
    }

    @Test
    fun `gueltige Set mit drei Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        // when
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)
        //then
        assertThat(istGueltig).isTrue()
    }

    @Test
    fun `ungueltige Set mit drei Steine, selbe Zahl (Sechs) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //then
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isFalse()
    }

    @Test
    fun `gueltige Set mit drei Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        // when
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)
        //then
        assertThat(istGueltig).isTrue()
    }

    @Test
    fun `ungueltige Set mit drei Steine, selbe Zahl (Dreizehn) und unterschiedliche Farben`() {
        val set = listOf(
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn)
        )
        //then
        val regelPruefer = Sets()
        val istGueltig = regelPruefer.istGueltigesSet(set)

        //then
        assertThat(istGueltig).isFalse()
    }
}