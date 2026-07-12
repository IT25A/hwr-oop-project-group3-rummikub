package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ZugTest {
	private fun createZug(
		aktiverSpieler: SpielerId = SpielerId("spieler1"),
		kombis: List<Kombinationen> = emptyList(),
		angelegt: List<Pair<Kombinationen, Stein>> = emptyList(),
		auseinandergezogen: List<Kombinationen> = emptyList(),
		gezogen: Stein = Stein(Farbe.Rot, Zahl.Eins),
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
		val spieler = SpielerId("p1")
		val stein = Stein(Farbe.Blau, Zahl.Zwei)
		val kombi =
			Sets(mutableListOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Blau, Zahl.Eins), Stein(Farbe.Orange, Zahl.Eins)))
		val angelegt = Pair(kombi, Stein(Farbe.Schwarz, Zahl.Eins))
		val aufgeloest =
			Folge(mutableListOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Rot, Zahl.Zwei), Stein(Farbe.Rot, Zahl.Drei)))
		
		val zug = Zug(
			aktiverSpieler = spieler,
			kombis = listOf(kombi),
			angelegt = listOf(angelegt),
			auseinandergezogen = listOf(aufgeloest),
			gezogen = stein
		)
		
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
		val zug1 = createZug()
		val zug2 = createZug()
		
		assertEquals(zug1, zug2)
		assertEquals(zug1.hashCode(), zug2.hashCode())
	}
	
	@Test
	fun `Zug objects with different active players are not equal`() {
		val zug1 = createZug(
			aktiverSpieler = SpielerId("spieler1")
		)
		
		val zug2 = createZug(
			aktiverSpieler = SpielerId("spieler2")
		)
		
		assertNotEquals(zug1, zug2)
	}
	
	@Test
	fun `Zug objects with different drawn stones are not equal`() {
		val zug1 = createZug(
			gezogen = Stein(Farbe.Rot, Zahl.Eins)
		)
		
		val zug2 = createZug(
			gezogen = Stein(Farbe.Blau, Zahl.Zwei)
		)
		
		assertNotEquals(zug1, zug2)
	}
	
	@Test
	fun `copy creates equal Zug with changed value`() {
		val original = createZug()
		
		val copy = original.copy(
			aktiverSpieler = SpielerId("spieler2")
		)
		
		assertNotEquals(original, copy)
		assertEquals(SpielerId("spieler2"), copy.aktiverSpieler)
		assertEquals(original.gezogen, copy.gezogen)
	}
	
	@Test
	fun `toString contains class information`() {
		val zug = createZug()
		
		val text = zug.toString()
		
		assertTrue(text.contains("Zug"))
		assertTrue(text.contains("spieler1"))
	}
}