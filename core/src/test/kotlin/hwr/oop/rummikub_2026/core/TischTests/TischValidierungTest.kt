package hwr.oop.rummikub_2026.core.TischTests

import hwr.oop.rummikub_2026.core.Kombinationen
import hwr.oop.rummikub_2026.core.Tisch
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TischValidierungTest {
	
	companion object {
		@JvmStatic
		fun gueltigeFolgen() = TischTestData.gueltigeFolgen()
		
		@JvmStatic
		fun gueltigeSets4Steine() = TischTestData.gueltigeSets4Steine()
		
		@JvmStatic
		fun gueltigeSets3Steine() = TischTestData.gueltigeSets3Steine()
		
		@JvmStatic
		fun ungueltigeKombi() = TischTestData.ungueltigeKombi()
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
	fun `Sets und Folgen sind gueltig im Tisch`(
      kombi: Kombinationen,
  ) {
		val testTisch = Tisch(mutableListOf(kombi))
		
		assertDoesNotThrow {
			testTisch.gueltig()
		}
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeKombi")
	fun `Sets und Folgen sind nicht gueltig im Tisch`(
      kombi: Kombinationen,
  ) {
		val testTisch = Tisch(mutableListOf(kombi))
		
		assertThrows<IllegalArgumentException> {
			testTisch.gueltig()
		}
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
	fun `Folgen und Sets koennen auf dem Tisch liegen`(
      kombi: Kombinationen,
  ) {
		val testTisch = Tisch(mutableListOf(kombi))
		
		assertThat(testTisch.tischReadOnly).contains(kombi)
	}
}