package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ZugTest {

    private fun createZug(
        aktiverSpieler: SpielerId = SpielerId("spieler1"),
        kombis: List<Kombinationen> = emptyList(),
        angelegt: List<Pair<Kombinationen, Stein>> = emptyList(),
        auseinandergezogen: List<Kombinationen> = emptyList(),
        gezogen: Stein = Stein(Farbe.Rot, Zahl.Eins)
    ): Zug {
        return Zug(
            aktiverSpieler,
            kombis,
            angelegt,
            auseinandergezogen,
            gezogen
        )
    }

    @Test
    fun `Zug stores all given values`() {
        // Arrange
        val spieler = SpielerId("p1")
        val stein = Stein(Farbe.Blau, Zahl.Zwei)
        val kombi = Sets(mutableListOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Blau, Zahl.Eins), Stein(Farbe.Orange, Zahl.Eins)))
        val angelegt = Pair(kombi, Stein(Farbe.Schwarz, Zahl.Eins))
        val aufgeloest = Folge(mutableListOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Rot, Zahl.Zwei), Stein(Farbe.Rot, Zahl.Drei)))

        val zug = Zug(
            aktiverSpieler = spieler,
            kombis = listOf(kombi),
            angelegt = listOf(angelegt),
            auseinandergezogen = listOf(aufgeloest),
            gezogen = stein
        )

        // Assert
        assertEquals(spieler, zug.aktiverSpieler)
        assertEquals(stein, zug.gezogen)
        assertEquals(1, zug.kombis.size)
        assertEquals(kombi, zug.kombis[0])
        assertEquals(1, zug.angelegt.size)
        assertEquals(angelegt, zug.angelegt[0])
        assertEquals(1, zug.auseinandergezogen.size)
        assertEquals(aufgeloest, zug.auseinandergezogen[0])
    }

    @Test
    fun `two Zug objects with same values are equal`() {
        // Arrange
        val zug1 = createZug()
        val zug2 = createZug()

        // Assert
        assertEquals(zug1, zug2)
        assertEquals(zug1.hashCode(), zug2.hashCode())
    }

    @Test
    fun `Zug objects with different active players are not equal`() {
        // Arrange
        val zug1 = createZug(
            aktiverSpieler = SpielerId("spieler1")
        )
        val zug2 = createZug(
            aktiverSpieler = SpielerId("spieler2")
        )

        // Assert
        assertNotEquals(zug1, zug2)
    }

    @Test
    fun `Zug objects with different drawn stones are not equal`() {
        // Arrange
        val zug1 = createZug(
            gezogen = Stein(Farbe.Rot, Zahl.Eins)
        )
        val zug2 = createZug(
            gezogen = Stein(Farbe.Blau, Zahl.Zwei)
        )

        // Assert
        assertNotEquals(zug1, zug2)
    }

    @Test
    fun `copy creates equal Zug with changed value`() {
        // Arrange
        val original = createZug()

        // Act
        val copy = original.copy(
            aktiverSpieler = SpielerId("spieler2")
        )

        // Assert
        assertNotEquals(original, copy)
        assertEquals(SpielerId("spieler2"), copy.aktiverSpieler)
        assertEquals(original.gezogen, copy.gezogen)
    }

    @Test
    fun `toString contains class information`() {
        // Arrange
        val zug = createZug()

        // Act
        val text = zug.toString()

        // Assert
        assertTrue(text.contains("Zug"))
        assertTrue(text.contains("spieler1"))
    }
}