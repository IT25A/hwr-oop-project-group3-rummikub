package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.Kombinationen
import hwr.oop.rummikub_2026.core.Spiel
import hwr.oop.rummikub_2026.core.SpielTests.SpielTestData.erstelle14Steine
import hwr.oop.rummikub_2026.core.Spieler
import hwr.oop.rummikub_2026.core.Tisch
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SpielValidierungTest {
	
	companion object {
		@JvmStatic
		fun gueltigeFolgen() = SpielTestData.gueltigeFolgen()
		
		@JvmStatic
		fun gueltigeSets4Steine() = SpielTestData.gueltigeSets4Steine()
		
		@JvmStatic
		fun gueltigeSets3Steine() = SpielTestData.gueltigeSets3Steine()
		
		@JvmStatic
		fun ungueltigeKombi() = SpielTestData.ungueltigeKombi()
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
	fun `Sets und Folgen sind gueltig im Tisch`(
      kombi: Kombinationen,
  ) {
		val spiel = Spiel(
			aktivSpieler = Spieler(
				"Maxi-Taxi",
				"1",
				erstelle14Steine(),
				true
			),
			beutel = emptyList(),
			tisch = Tisch(mutableListOf(kombi))
		)
		
		Assertions.assertDoesNotThrow {
			spiel.gueltigerZug()
		}
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeKombi")
	fun `Sets und Folgen sind nicht gueltig im Tisch`(
      kombi: Kombinationen,
  ) {
		val spiel = Spiel(
			aktivSpieler = Spieler(
				"spieler1",
				"1",
				erstelle14Steine(),
				true
			),
			beutel = emptyList(),
			tisch = Tisch(mutableListOf(kombi))
		)
		
		assertThrows<IllegalArgumentException> {
			spiel.gueltigerZug()
		}
	}
}