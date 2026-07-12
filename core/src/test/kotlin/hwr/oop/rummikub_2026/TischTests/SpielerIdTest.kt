package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class SpielerIdTest {
	@Test
	fun `SpielerId stores given value`() {
		val id = SpielerId("spieler1")
		
		assertEquals("spieler1", id.value)
	}
	
	@Test
	fun `SpielerId with same value is equal`() {
		val id1 = SpielerId("spieler1")
		val id2 = SpielerId("spieler1")
		
		assertEquals(id1, id2)
		assertEquals(id1.hashCode(), id2.hashCode())
	}
	
	@Test
	fun `SpielerId with different values is not equal`() {
		val id1 = SpielerId("spieler1")
		val id2 = SpielerId("spieler2")
		
		assertNotEquals(id1, id2)
	}
	
	@Test
	fun `SpielerId supports empty string`() {
		val id = SpielerId("")
		
		assertEquals("", id.value)
	}
}