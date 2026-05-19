package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class SetsBearbeitenTest {

    // Hinzufuegen zu 3er Set, Voraussetzung: Anlegen ist valide
    // Hinzufuegen zu 4er Set, Anlegen ist nicht valide
    // Wegnehmen vom 4er Set, Voraussetzung: gueltiges Set oder gueltige Folge
    // Wegnehmen vom 3er Set nicht valide

    //Hinzufuegen und Wegnehmen: Abfragen, ob gueltig/ nicht gueltig implementiert
    //Hinzufuegen und Wegnehmen: Funktion für das eigentliche Hinzufuegen/ Wegnehmen fehlt noch

    @Test
    fun `Hinzufuegen zu 3er Set Eins = gueltiges 4er Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins)
        )
        val sets = Sets(set)
        sets.istGueltigesSet()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `Hinzufuegen zu 3er Set Sechs = gueltiges 4er Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs)
        )
        val sets = Sets(set)
        sets.istGueltigesSet()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `Hinzufuegen zu 3er Set Dreizehn = gueltiges 4er Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
        val sets = Sets(set)
        sets.istGueltigesSet()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }


    @Test
    fun `Hinzufuegen zu 4er Set Eins = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins)
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltigesSet()
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `Hinzufuegen zu 4er Set Sechs = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Schwarz, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs)
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltigesSet()
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `Hinzufuegen zu 4er Set Dreizehn = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
            Stein(Farbe.Schwarz, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn)
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltigesSet()
        }
        assertThat(exception.message).contains("Maximal 4 Steine")
    }

    @Test
    fun `Wegnehmen vom 4er Set Eins = gueltiges Set oder gueltige Folge` () {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins),
        )
        //when
        val sets = Sets(set)
        sets.istGueltigesSet()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `Wegnehmen vom 4er Set Sechs = gueltiges Set oder gueltige Folge` () {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sechs),
        )
        //when
        val sets = Sets(set)
        sets.istGueltigesSet()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `Wegnehmen vom 4er Set Dreizehn = gueltiges Set oder gueltige Folge` () {
        // given
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
            Stein(Farbe.Blau, Zahl.Dreizehn),
        )
        //when
        val sets = Sets(set)
        sets.istGueltigesSet()

        //then
        assertThat(sets.setReadOnly).isEqualTo(set)
    }

    @Test
    fun `Wegnehmen zu 3er Set Eins = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins),
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltigesSet()
        }
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }

    @Test
    fun `Wegnehmen zu 3er Set Sechs = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Sechs),
            Stein(Farbe.Rot, Zahl.Sechs),
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltigesSet()
        }
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }

    @Test
    fun `Wegnehmen zu 3er Set Dreizehn = ungueltiges Set` () {
        val set = mutableListOf(
            Stein(Farbe.Orange, Zahl.Dreizehn),
            Stein(Farbe.Rot, Zahl.Dreizehn),
        )
        val sets = Sets(set)

        //then
        val exception = assertThrows<IllegalArgumentException> {
            sets.istGueltigesSet()
        }
        assertThat(exception.message).contains("Mindestens 3 Steine")
    }
}