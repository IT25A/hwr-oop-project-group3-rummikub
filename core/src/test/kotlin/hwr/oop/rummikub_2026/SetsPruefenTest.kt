package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Sets
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Zahl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SetsPruefenTest {
	
	companion object {
		@JvmStatic
		fun gueltigeSets3Steine() = listOf(
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Rot, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Eins)
			),
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Sechs),
				Stein(Farbe.Blau, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sechs)
			),
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Dreizehn),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			)
		)
		
		@JvmStatic
		fun gueltigeSets4Steine() = listOf(
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Rot, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Eins)
			),
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Sechs),
				Stein(Farbe.Rot, Zahl.Sechs),
				Stein(Farbe.Blau, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sechs)
			),
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Dreizehn),
				Stein(Farbe.Rot, Zahl.Dreizehn),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			)
		)
		
		@JvmStatic
		fun ungueltigeSetsGleicheFarbe() = listOf(
			Pair(
				mutableListOf(
					Stein(Farbe.Rot, Zahl.Eins),
					Stein(Farbe.Rot, Zahl.Eins),
					Stein(Farbe.Blau, Zahl.Eins),
					Stein(Farbe.Schwarz, Zahl.Eins)
				),
				"Alle Steine muessen unterschiedliche Farbe haben"
			),
			Pair(
				mutableListOf(
					Stein(Farbe.Orange, Zahl.Sechs),
					Stein(Farbe.Orange, Zahl.Sechs),
					Stein(Farbe.Blau, Zahl.Sechs),
					Stein(Farbe.Schwarz, Zahl.Sechs)
				),
				"Alle Steine muessen unterschiedliche Farbe haben"
			),
			Pair(
				mutableListOf(
					Stein(Farbe.Orange, Zahl.Dreizehn),
					Stein(Farbe.Blau, Zahl.Dreizehn),
					Stein(Farbe.Blau, Zahl.Dreizehn),
					Stein(Farbe.Schwarz, Zahl.Dreizehn)
				),
				"Alle Steine muessen unterschiedliche Farbe haben"
			),
			Pair(
				mutableListOf(
					Stein(Farbe.Orange, Zahl.Eins),
					Stein(Farbe.Orange, Zahl.Eins),
					Stein(Farbe.Blau, Zahl.Eins)
				),
				"Alle Steine muessen unterschiedliche Farbe haben"
			),
			Pair(
				mutableListOf(
					Stein(Farbe.Orange, Zahl.Sechs),
					Stein(Farbe.Schwarz, Zahl.Sechs),
					Stein(Farbe.Schwarz, Zahl.Sechs)
				),
				"Alle Steine muessen unterschiedliche Farbe haben"
			),
			Pair(
				mutableListOf(
					Stein(Farbe.Rot, Zahl.Dreizehn),
					Stein(Farbe.Blau, Zahl.Dreizehn),
					Stein(Farbe.Blau, Zahl.Dreizehn)
				),
				"Alle Steine muessen unterschiedliche Farbe haben"
			)
		)
		
		@JvmStatic
		fun ungueltigeSetsUnterschiedlicheZahl() = listOf(
			Pair(
				mutableListOf(
					Stein(Farbe.Orange, Zahl.Eins),
					Stein(Farbe.Rot, Zahl.Dreizehn),
					Stein(Farbe.Blau, Zahl.Dreizehn),
					Stein(Farbe.Schwarz, Zahl.Dreizehn)
				),
				"Alle Steine muessen dieselbe Zahl haben"
			),
			Pair(
				mutableListOf(
					Stein(Farbe.Orange, Zahl.Eins),
					Stein(Farbe.Rot, Zahl.Dreizehn),
					Stein(Farbe.Blau, Zahl.Dreizehn)
				),
				"Alle Steine muessen dieselbe Zahl haben"
			)
		)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeSets3Steine")
	fun `gueltige Sets mit 3 Steinen werfen keine Exception`(
		set: MutableList<Stein>,
  ) {
		// when
		val sets = Sets(set)
		
		sets.istGueltig()
		// then
		assertThat(sets.setReadOnly).isEqualTo(set)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeSets4Steine")
	fun `gueltige Sets mit 4 Steinen werfen keine Exception`(
		set: MutableList<Stein>,
  ) {
		// when
		val sets = Sets(set)
		// then
		assertThat(sets.setReadOnly).isEqualTo(set)
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeSetsGleicheFarbe")
	fun `ungueltige Sets mit gleichen Farben werfen Exception`(
		testfall: Pair<MutableList<Stein>, String>,
  ) {
		// when
		val sets = Sets(testfall.first)
		// then
		val exception = assertThrows<IllegalArgumentException> {
			sets.istGueltig()
		}
		
		assertThat(exception.message).contains(testfall.second)
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeSetsUnterschiedlicheZahl")
	fun `ungueltige Sets mit unterschiedlichen Zahlen werfen Exception`(
		testfall: Pair<MutableList<Stein>, String>,
  ) {
		// when
		val sets = Sets(testfall.first)
		// then
		val exception = assertThrows<IllegalArgumentException> {
			sets.istGueltig()
		}
		
		assertThat(exception.message).contains(testfall.second)
	}
}