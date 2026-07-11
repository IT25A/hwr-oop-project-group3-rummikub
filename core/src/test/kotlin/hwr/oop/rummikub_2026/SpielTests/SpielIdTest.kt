package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class SpielIdTest {

    @Test
    fun `SpielId stores given value`() {
        // Arrange
        val id = SpielId("test-id")

        // Assert
        assertEquals("test-id", id.wert)
    }

    @Test
    fun `random creates valid UUID SpielId`() {
        // Act
        val id = SpielId.random()

        // Assert
        assertDoesNotThrow {
            UUID.fromString(id.wert)
        }
    }

    @Test
    fun `random creates different ids`() {
        // Act
        val id1 = SpielId.random()
        val id2 = SpielId.random()

        // Assert
        assertNotEquals(id1, id2)
    }

    @Test
    fun `from creates SpielId from UUID`() {
        // Arrange
        val uuid = UUID.randomUUID()

        // Act
        val id = SpielId.from(uuid)

        // Assert
        assertEquals(uuid.toString(), id.wert)
    }

    @Test
    fun `uuid converts SpielId back to UUID`() {
        // Arrange
        val uuid = UUID.randomUUID()
        val id = SpielId(uuid.toString())

        // Act
        val result = id.uuid()

        // Assert
        assertEquals(uuid, result)
    }

    @Test
    fun `uuid throws exception for invalid UUID string`() {
        // Arrange
        val id = SpielId("not-a-valid-uuid")

        // Act + Assert
        assertThrows<IllegalArgumentException> {
            id.uuid()
        }

    }
}