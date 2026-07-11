package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SpielEqualsTest {

    private fun createSpiel(
        aktivSpieler: Spieler = createSpieler("p1"),
        beutel: List<Stein> = listOf(Stein(Farbe.Rot, Zahl.Eins)),
        tisch: Tisch = Tisch(mutableListOf()),
        id: SpielId = SpielId("game1")
    ): Spiel {
        return Spiel(
            aktivSpieler = aktivSpieler,
            beutel = beutel,
            tisch = tisch,
            spieler = listOf(aktivSpieler),
            id = id
        )
    }

    private fun createSpieler(id: String): Spieler {
        return Spieler(
            name = "Spieler $id",
            id = SpielerId(id),
            brett = mutableListOf(),
            validateInitialCount = false
        )
    }

    @Test
    fun `same Spiel instance is equal`() {
        // Arrange
        val spiel = createSpiel()

        // Assert
        assertEquals(spiel, spiel)
    }

    @Test
    fun `two Spiele with same values are equal`() {
        // Arrange
        val spiel1 = createSpiel()
        val spiel2 = createSpiel()

        // Assert
        assertEquals(spiel1, spiel2)
    }

    @Test
    fun `Spiel with different active player is not equal`() {
        // Arrange
        val spiel1 = createSpiel(
            aktivSpieler = createSpieler("p1")
        )
        val spiel2 = createSpiel(
            aktivSpieler = createSpieler("p2")
        )

        // Assert
        assertNotEquals(spiel1, spiel2)
    }

    @Test
    fun `Spiel with different bag is not equal`() {
        // Arrange
        val spiel1 = createSpiel(
            beutel = listOf(Stein(Farbe.Rot, Zahl.Eins))
        )
        val spiel2 = createSpiel(
            beutel = listOf(Stein(Farbe.Blau, Zahl.Zwei))
        )

        // Assert
        assertNotEquals(spiel1, spiel2)
    }

    @Test
    fun `Spiel with different table is not equal`() {
        // Arrange
        val tisch1 = Tisch(
            mutableListOf(Folge(mutableListOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Rot, Zahl.Zwei), Stein(Farbe.Rot, Zahl.Drei))))
        )
        val tisch2 = Tisch(
            mutableListOf()
        )

        val spiel1 = createSpiel(tisch = tisch1)
        val spiel2 = createSpiel(tisch = tisch2)

        // Assert
        assertNotEquals(spiel1, spiel2)
    }

    @Test
    fun `Spiel with different id is not equal`() {
        // Arrange
        val spiel1 = createSpiel(
            id = SpielId("game1")
        )
        val spiel2 = createSpiel(
            id = SpielId("game2")
        )

        // Assert
        assertNotEquals(spiel1, spiel2)
    }

    @Test
    fun `Spiel compared with another object is not equal`() {
        // Arrange
        val spiel = createSpiel()

        // Assert
        assertNotEquals(spiel, "not a game")
    }

    @Test
    fun `Spiel compared with null is not equal`() {
        // Arrange
        val spiel = createSpiel()

        // Assert
        assertNotEquals(spiel, null)
    }
}