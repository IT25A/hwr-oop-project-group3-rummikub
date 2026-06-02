package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Assertions.assertDoesNotThrow

class SetsBearbeitenTest {

    @Test
    fun `Hinzufuegen zu 3er Set Eins = gueltiges 4er Set` () {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins)
        )
        )
        //then
        assertDoesNotThrow {
            set.hinzufuegenZumSet(Stein(Farbe.Schwarz, Zahl.Eins))
        }
        assertThat(Stein(Farbe.Schwarz, Zahl.Eins) in set.setReadOnly).isTrue
    }

    @Test
    fun `Hinzufuegen zu 3er Set Sechs = gueltiges 4er Set` () {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        )//when
        assertDoesNotThrow {
            set.hinzufuegenZumSet(Stein(Farbe.Blau, Zahl.Sechs))
        }
        assertThat(Stein(Farbe.Blau, Zahl.Sechs) in set.setReadOnly).isTrue
    }

    @Test
    fun `Hinzufuegen zu 3er Set Dreizehn = gueltiges 4er Set` () {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
        ))
        //then
        assertDoesNotThrow {
            set.hinzufuegenZumSet(Stein(Farbe.Schwarz, Zahl.Dreizehn))
        }
        assertThat(Stein(Farbe.Schwarz, Zahl.Eins) in set.setReadOnly)
    }

    @Test
    fun `Hinzufuegen zu 4er Set Eins = ungueltiges Set`() {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        ))

        //then
        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(Stein(Farbe.Schwarz, Zahl.Eins))
            set.istGueltig()
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `Hinzufuegen zu 4er Set Sechs = ungueltiges Set` () {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        ))

        //then
        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(Stein(Farbe.Schwarz, Zahl.Sechs))
            set.istGueltig()
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `Hinzufuegen zu 4er Set Dreizehn = ungueltiges Set` () {
        val set = Sets(mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        ))

        //then
        val exception = assertThrows<IllegalArgumentException> {
            set.hinzufuegenZumSet(Stein(Farbe.Schwarz, Zahl.Sechs))
            set.istGueltig()
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `Wegnehmen vom 4er Set Eins = gueltiges Set oder gueltige Folge` () {

        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        )
        val sets = Sets(set)

        sets.wegnehmenVomSet(Stein(Farbe.Rot, Zahl.Eins))
        sets.istGueltig()

        assertThat(sets.setReadOnly).doesNotContain(Stein(Farbe.Rot, Zahl.Eins))
    }


    @Test
    fun `Wegnehmen vom 4er Set Sechs = gueltiges Set oder gueltige Folge` () {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        //when
        val sets = Sets(set)
        sets.wegnehmenVomSet(Stein(Farbe.Blau, Zahl.Sechs))
        sets.istGueltig()

        assertThat(sets.setReadOnly).doesNotContain(Stein(Farbe.Blau, Zahl.Sechs))
    }

    @Test
    fun `Wegnehmen vom 4er Set Dreizehn = gueltiges Set oder gueltige Folge` () {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        //when
        val sets = Sets(set)

        sets.wegnehmenVomSet(Stein(Farbe.Orange, Zahl.Dreizehn))
        sets.istGueltig()

        assertThat(sets.setReadOnly).doesNotContain(Stein(Farbe.Orange, Zahl.Dreizehn))
    }

    @Test
    fun `Wegnehmen zu 3er Set Eins = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins),
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.wegnehmenVomSet(Stein(Farbe.Orange, Zahl.Eins))
            sets.istGueltig()
        }
        assertThat(sets.setReadOnly).doesNotContain(Stein(Farbe.Orange, Zahl.Eins))
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }

    @Test
    fun `Wegnehmen zu 3er Set Sechs = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.wegnehmenVomSet(Stein(Farbe.Rot, Zahl.Sechs))
            sets.istGueltig()
        }
        assertThat(sets.setReadOnly).doesNotContain(Stein(Farbe.Rot, Zahl.Sechs))
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }

    @Test
    fun `Wegnehmen zu 3er Set Dreizehn = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.wegnehmenVomSet(Stein(Farbe.Schwarz, Zahl.Dreizehn))
            sets.istGueltig()
        }
        assertThat(sets.setReadOnly).doesNotContain(Stein(Farbe.Schwarz, Zahl.Dreizehn))
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }
}