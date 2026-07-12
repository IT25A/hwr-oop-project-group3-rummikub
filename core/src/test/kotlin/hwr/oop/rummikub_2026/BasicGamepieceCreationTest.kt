package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Zahl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class BasicPieceCreationTest {
	@Test
	fun `alle Zahlen existieren`() {
		val zahlen = Zahl.entries
		
		assertThat(zahlen).containsExactlyInAnyOrder(
			Zahl.Eins,
			Zahl.Zwei,
			Zahl.Drei,
			Zahl.Vier,
			Zahl.Fuenf,
			Zahl.Sechs,
			Zahl.Sieben,
			Zahl.Acht,
			Zahl.Neun,
			Zahl.Zehn,
			Zahl.Elf,
			Zahl.Zwoelf,
			Zahl.Dreizehn
		)
	}
	
	@ParameterizedTest
	@EnumSource(Farbe::class)
	fun `alle Zahlen, jede Farbe existiert`(farbe: Farbe) {
		val allZahlen = listOf(
			Zahl.Eins,
			Zahl.Zwei,
			Zahl.Drei,
			Zahl.Vier,
			Zahl.Fuenf,
			Zahl.Sechs,
			Zahl.Sieben,
			Zahl.Acht,
			Zahl.Neun,
			Zahl.Zehn,
			Zahl.Elf,
			Zahl.Zwoelf,
			Zahl.Dreizehn
		)
		
		val stein = allZahlen.map { Stein(farbe, it) }
		
		assertThat(stein)
			.hasSize(allZahlen.size)
			.allMatch { it.farbe() == farbe }
		
		val zahlen = stein.map { it.zahl() }
		
		assertThat(zahlen).containsExactlyInAnyOrderElementsOf(allZahlen)
	}
	
	@Test
	fun `alle Farben existieren`() {
		val farben = Farbe.entries
		
		assertThat(farben).containsExactlyInAnyOrder(
			Farbe.Orange,
			Farbe.Rot,
			Farbe.Blau,
			Farbe.Schwarz,
			Farbe.Joker
		)
	}
	
	@ParameterizedTest
	@EnumSource(Zahl::class)
	fun `alle  Farben, jede Zahl existiert`(zahl: Zahl) {
		val allFarben = listOf(
			Farbe.Orange,
			Farbe.Rot,
			Farbe.Blau,
			Farbe.Schwarz,
			Farbe.Joker
		)
		
		val cards = allFarben.map { Stein(it, zahl) }
		
		assertThat(cards)
			.hasSize(5)
			.allMatch { it.zahl() == zahl }
		
		val farben = cards.map { it.farbe() }
		
		assertThat(farben).containsExactlyInAnyOrderElementsOf(allFarben)
	}
}