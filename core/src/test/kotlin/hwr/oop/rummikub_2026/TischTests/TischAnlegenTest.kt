package hwr.oop.rummikub_2026.TischTests

import hwr.oop.rummikub_2026.core.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TischAnlegenTest {
	
	companion object {
		@JvmStatic
		fun gueltigeSets3Steine() = TischTestData.gueltigeSets3Steine()
		
		@JvmStatic
		fun gueltigeSets4Steine() = TischTestData.gueltigeSets4Steine()
		
		@JvmStatic
		fun gueltigeFolgen() = TischTestData.gueltigeFolgen()
		
		@JvmStatic
		fun anlegenAusserhalb() = TischTestData.anlegenAusserhalb()
		
		@JvmStatic
		fun anlegenGrenzfallSzenarien() = TischTestData.anlegenGrenzfallSzenarien()
		
		@JvmStatic
		fun anlegenSetsSzenarien() = TischTestData.anlegenSetsSzenarien()
		
		@JvmStatic
		fun anlegenFolgenSzenarien() = TischTestData.anlegenFolgenSzenarien()
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeSets3Steine", "gueltigeSets4Steine")
	fun `anlegen funktioniert set`(
      set: Sets,
  ) {
		val tisch = Tisch(mutableListOf(set))
		
		tisch.anlegen(
			0,
			Stein(Farbe.Orange, Zahl.Eins)
		)
		
		assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeFolgen")
	fun `anlegen funktioniert folge`(
      folge: Folge,
  ) {
		val tisch = Tisch(mutableListOf(folge))
		
		tisch.anlegen(
			0,
			Stein(Farbe.Orange, Zahl.Eins)
		)
		
		assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
	}
	
	@ParameterizedTest
	@MethodSource("anlegenAusserhalb")
	fun `anlegen ausserhalb der Tischgroesse wirft Exception`(
      testfall: Pair<List<Kombinationen>, Int>,
  ) {
		val kombis = testfall.first
		val position = testfall.second
		val tisch = Tisch(kombis.toMutableList())
		
		val exception = assertThrows<IndexOutOfBoundsException> {
			tisch.anlegen(
				position,
				Stein(Farbe.Rot, Zahl.Eins)
			)
		}
		assertThat(exception.message).isEqualTo("Die Kombi gibt es nicht")
	}
	
	@ParameterizedTest
	@MethodSource("anlegenGrenzfallSzenarien")
	fun `anlegen auf der letzten Position funktioniert`(
      testfall: Triple<List<Kombinationen>, Int, Stein>,
  ) {
		val kombis = testfall.first
		val position = testfall.second
		val stein = testfall.third
		val tisch = Tisch(kombis.toMutableList())
		
		assertDoesNotThrow {
			tisch.anlegen(
				position,
				stein
			)
		}
		
		assertThat(tisch.tischReadOnly[position].get()).contains(stein)
	}
	
	@ParameterizedTest
	@MethodSource("anlegenSetsSzenarien")
	fun `anlegen an Sets erkennt Sets korrekt`(
      testfall: Pair<Sets, Stein>,
  ) {
		val set = testfall.first
		val stein = testfall.second
		val tisch = Tisch(mutableListOf(set))
		
		tisch.anlegen(
			0,
			stein
		)
		
		assertThat(tisch.tischReadOnly[0]).isInstanceOf(Sets::class.java)
		assertThat(tisch.tischReadOnly[0].get()).contains(stein)
	}
	
	@ParameterizedTest
	@MethodSource("anlegenFolgenSzenarien")
	fun `anlegen an Folge erkennt Folge korrekt`(
      testfall: Pair<Folge, Stein>,
  ) {
		val folge = testfall.first
		val stein = testfall.second
		val tisch = Tisch(mutableListOf(folge))
		
		tisch.anlegen(
			0,
			stein
		)
		
		assertThat(tisch.tischReadOnly[0]).isInstanceOf(Folge::class.java)
		assertThat(tisch.tischReadOnly[0].get()).contains(stein)
	}
}