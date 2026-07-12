package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SteineAusteilenTest {
	@Test
	fun `austeilen creates correct number of hands with 14 stones each`() {
		val steine = mutableListOf<Stein>()
		
		for (i in 0 until 28) {
			steine.add(
				Stein(
					Farbe.entries[i % Farbe.entries.size],
					Zahl.entries[i % Zahl.entries.size]
				)
			)
		}
		
		val originalSteine = steine.toList()
		
		val beutel = Beutel(steine)
		
		val ausgeteilt = beutel.austeilen(2)
		
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
		val beutel = Beutel(
			mutableListOf(
				Stein(Farbe.entries.first(), Zahl.entries.first())
			)
		)
		
		val result = beutel.austeilen(0)
		
		assertTrue(result.isEmpty())
	}
	
	@Test
	fun `austeilen does not change bag size`() {
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
		
		beutel.austeilen(2)
		
		assertFalse(initialSize == beutel.anzahlSteine())
	}
}