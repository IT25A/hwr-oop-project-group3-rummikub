package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class SpielerIdTest {

    @Test
    fun `SpielerId stores given value`() {
        // Arrange
        val id = SpielerId("spieler1")

        // Assert
        assertEquals("spieler1", id.value)
    }

    @Test
    fun `SpielerId with same value is equal`() {
        // Arrange
        val id1 = SpielerId("spieler1")
        val id2 = SpielerId("spieler1")

        // Assert
        assertEquals(id1, id2)
        assertEquals(id1.hashCode(), id2.hashCode())
    }

    @Test
    fun `SpielerId with different values is not equal`() {
        // Arrange
        val id1 = SpielerId("spieler1")
        val id2 = SpielerId("spieler2")

        // Assert
        assertNotEquals(id1, id2)
    }

    @Test
    fun `SpielerId supports empty string`() {
        // Arrange
        val id = SpielerId("")

        // Assert
        assertEquals("", id.value)
    }
}