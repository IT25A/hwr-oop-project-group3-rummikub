package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SteineAusteilenTest {

    @Test
    fun `austeilen creates correct number of hands with 14 stones each`() {
        // Arrange: create a deterministic bag with 28 stones
        val steine = mutableListOf<Stein>()

        for (i in 0 until 28) {
            steine.add(
                Stein(
                    Farbe.entries[i % Farbe.entries.size],
                    Zahl.entries[i % Zahl.entries.size]
                )
            )
        }

        // WICHTIG: Mache eine Kopie der Liste, bevor der Beutel sie leert!
        val originalSteine = steine.toList()

        val beutel = Beutel(steine)

        // Act
        val ausgeteilt = beutel.austeilen(2)

        // Assert
        assertEquals(2, ausgeteilt.size)

        assertEquals(14, ausgeteilt[0].size)
        assertEquals(14, ausgeteilt[1].size)

        assertEquals(originalSteine[0], ausgeteilt[0][0])
        assertEquals(originalSteine[13], ausgeteilt[0][13])

        assertEquals(originalSteine[14], ausgeteilt[1][0])
        assertEquals(originalSteine[27], ausgeteilt[1][13])
    }

    @Test
    fun `austeilen with zero players returns empty list`() {
        // Arrange
        val beutel = Beutel(
            mutableListOf(
                Stein(Farbe.entries.first(), Zahl.entries.first())
            )
        )

        // Act
        val result = beutel.austeilen(0)

        // Assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `austeilen does not change bag size`() {
        // Arrange
        val steine = mutableListOf<Stein>()

        repeat(28) {
            steine.add(
                Stein(
                    Farbe.entries.first(),
                    Zahl.entries.first()
                )
            )
        }

        val beutel = Beutel(steine)
        val initialSize = beutel.anzahlSteine()

        // Act
        beutel.austeilen(2)

        // Assert
        assertFalse(initialSize == beutel.anzahlSteine())
    }
}