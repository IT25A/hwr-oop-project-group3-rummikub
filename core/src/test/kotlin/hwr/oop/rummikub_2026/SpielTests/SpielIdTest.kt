package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class SpielIdTest {
	@Test
	fun `SpielId stores given value`() {
		val id = SpielId("test-id")
		
		assertEquals("test-id", id.wert)
	}
	
	@Test
	fun `random creates valid UUID SpielId`() {
		val id = SpielId.random()
		
		assertDoesNotThrow {
			UUID.fromString(id.wert)
		}
	}
	
	@Test
	fun `random creates different ids`() {
		val id1 = SpielId.random()
		val id2 = SpielId.random()
		
		assertNotEquals(id1, id2)
	}
	
	@Test
	fun `from creates SpielId from UUID`() {
		val uuid = UUID.randomUUID()
		
		val id = SpielId.from(uuid)
		
		assertEquals(uuid.toString(), id.wert)
	}
	
	@Test
	fun `uuid converts SpielId back to UUID`() {
		val uuid = UUID.randomUUID()
		val id = SpielId(uuid.toString())
		
		val result = id.uuid()
		
		assertEquals(uuid, result)
	}
	
	@Test
	fun `uuid throws exception for invalid UUID string`() {
		val id = SpielId("not-a-valid-uuid")
		
		assertThrows<IllegalArgumentException> {
			id.uuid()
		}
	}
}