package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class SetsBearbeitenTest {

    // Hinzufuegen zu 3er Set, Voraussetzung: Anlegen ist valide
    // Frage: ein Test ausreihend für alle Zahlen?

    // Hinzufuegen zu 4er Set, Anlegen ist nicht valide
    // Wegnehmen vom 4er Set, Voraussetzung: gueltiges Set oder gueltige Folge
    // Frage: ein Test ausreichend für alle Zahlen?

    // Wegnehmen vom 3er Set nicht valide
    

    @Test
    fun `Hinzufuegen zu 3er Set = gueltiges 4er Set` () {
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
    fun `Hinzufuegen zu 4er Set = ungueltiges Set` () {
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
    fun `Wegnehmen vom 4er Set = gueltiges Set oder gueltige Folge` () {
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
    fun `Wegnehmen zu 3er Set = ungueltiges Set` () {
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
}