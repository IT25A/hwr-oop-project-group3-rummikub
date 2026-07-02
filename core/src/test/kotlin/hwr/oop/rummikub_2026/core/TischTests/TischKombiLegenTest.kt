package hwr.oop.rummikub_2026.core.TischTests

import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Tisch
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TischKombiLegenTest {
	
	companion object {
		@JvmStatic
		fun gueltigeSteineFuerFolgen() = TischTestData.gueltigeSteineFuerFolgen()
		
		@JvmStatic
		fun gueltigeSteineFuerSets() = TischTestData.gueltigeSteineFuerSets()
		
		@JvmStatic
		fun ungueltigeSteineFuerSets() = TischTestData.ungueltigeSteineFuerSets()
		
		@JvmStatic
		fun ungueltigeSteineFuerFolgen() = TischTestData.ungueltigeSteineFuerFolgen()
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeSteineFuerFolgen")
	fun `kombi legen fuer Folge pruefen`(
      liste: MutableList<Stein>,
  ) {
		val tisch = Tisch(mutableListOf())
		
		tisch.kombiLegen(
			false,
			liste
		)
		
		assertThat(tisch.tischReadOnly).isNotEmpty()
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeSteineFuerSets")
	fun `kombi legen fuer Sets pruefen`(
      liste: MutableList<Stein>,
  ) {
		val tisch = Tisch(mutableListOf())
		
		tisch.kombiLegen(
			true,
			liste
		)
		
		assertThat(tisch.tischReadOnly).isNotEmpty()
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeSteineFuerSets")
	fun `kombi legen fuer ungueltige Sets wirft Exception`(
      liste: MutableList<Stein>,
  ) {
		val tisch = Tisch(mutableListOf())
		
		assertThrows<IllegalArgumentException> {
			tisch.kombiLegen(
				true,
				liste
			)
		}
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeSteineFuerFolgen")
	fun `kombi legen fuer ungueltige Folgen wirft Exception`(
      liste: MutableList<Stein>,
  ) {
		val tisch = Tisch(mutableListOf())
		
		assertThrows<IllegalArgumentException> {
			tisch.kombiLegen(
				false,
				liste
			)
		}
	}
}