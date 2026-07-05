package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Folge
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Zahl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class FolgeBearbeitenTest {
	
	companion object {
		
		private fun standardFolge() = Folge(
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Zwei),
				Stein(Farbe.Rot, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier)
			)
		)
		
		@JvmStatic
		fun gueltigeHintenSteine() = listOf(
			Stein(Farbe.Rot, Zahl.Fuenf)
		)
		
		@JvmStatic
		fun gueltigeVorneSteine() = listOf(
			Stein(Farbe.Rot, Zahl.Eins)
		)
		
		@JvmStatic
		fun ungueltigeHintenSteine() = listOf(
			// falsche Farbe
			Pair(
				Stein(Farbe.Blau, Zahl.Fuenf),
				"Alle Steine muessen die selbe Farbe haben"
			),
			
			// Lücke
			Pair(
				Stein(Farbe.Rot, Zahl.Sechs),
				"Steine muessen aufeinander Folgen"
			),
			
			// eigentlich vorne
			Pair(
				Stein(Farbe.Rot, Zahl.Eins),
				"Steine muessen aufeinander Folgen"
			),
			
			// doppelte Zahl
			Pair(
				Stein(Farbe.Rot, Zahl.Vier),
				"Steine muessen aufeinander Folgen"
			)
		)
		
		@JvmStatic
		fun ungueltigeVorneSteine() = listOf(
			
			// falsche Farbe
			Pair(
				Stein(Farbe.Blau, Zahl.Eins),
				"Alle Steine muessen die selbe Farbe haben"
			),
			
			// falsche Reihenfolge
			Pair(
				Stein(Farbe.Rot, Zahl.Sechs),
				"Steine muessen aufeinander Folgen"
			),
			
			// eigentlich hinten
			Pair(
				Stein(Farbe.Rot, Zahl.Fuenf),
				"Steine muessen aufeinander Folgen"
			),
			
			// doppelte Zahl
			Pair(
				Stein(Farbe.Rot, Zahl.Zwei),
				"Steine muessen aufeinander Folgen"
			)
		)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeHintenSteine")
	fun `gueltiges hinten hinzufuegen funktioniert`(
		stein: Stein,
  ) {
		// given
		val folge = standardFolge()
		
		// when
		assertDoesNotThrow {
			folge.hinzufuegenHinten(stein)
		}
		
		// then
		assertThat(folge.folgeReadOnly).contains(stein)
		
		assertThat(folge.folgeReadOnly.last()).isEqualTo(stein)
		
		assertThat(folge.folgeReadOnly).hasSize(4)
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeHintenSteine")
	fun `ungueltiges hinten hinzufuegen wirft exception`(
		testfall: Pair<Stein, String>,
  ) {
		// given
		val folge = standardFolge()
		
		// when
		val exception = assertThrows<IllegalArgumentException> {
			folge.hinzufuegenHinten(testfall.first)
		}
		
		// then
		assertThat(exception.message).contains(testfall.second)
	}
	
	@ParameterizedTest
	@MethodSource("gueltigeVorneSteine")
	fun `gueltiges vorne hinzufuegen funktioniert`(
		stein: Stein,
  ) {
		// given
		val folge = standardFolge()
		
		// when
		assertDoesNotThrow {
			folge.hinzufuegenVorne(stein)
		}
		
		// then
		assertThat(folge.folgeReadOnly).contains(stein)
		
		assertThat(folge.folgeReadOnly.first()).isEqualTo(stein)
		
		assertThat(folge.folgeReadOnly).hasSize(4)
	}
	
	@ParameterizedTest
	@MethodSource("ungueltigeVorneSteine")
	fun `ungueltiges vorne hinzufuegen wirft exception`(
		testfall: Pair<Stein, String>,
  ) {
		// given
		val folge = standardFolge()
		
		// when
		val exception = assertThrows<IllegalArgumentException> {
			folge.hinzufuegenVorne(testfall.first)
		}
		
		// then
		assertThat(exception.message).contains(testfall.second)
	}
	
	@Test
	fun `wegnehmen hinten entfernt letzten Stein`() {
		// given
		val folge = Folge(
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Zwei),
				Stein(Farbe.Rot, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Rot, Zahl.Fuenf)
			)
		)
		
		// when
		val entfernt = folge.wegnehmenHinten()
		
		// then
		assertThat(entfernt).isEqualTo(Stein(Farbe.Rot, Zahl.Fuenf))
		
		assertThat(folge.folgeReadOnly).doesNotContain(Stein(Farbe.Rot, Zahl.Fuenf))
		
		assertThat(folge.folgeReadOnly).hasSize(3)
	}
	
	@Test
	fun `wegnehmen vorne entfernt ersten Stein `() {
		// given
		val folge = Folge(
			mutableListOf(
				Stein(Farbe.Rot, Zahl.Zwei),
				Stein(Farbe.Rot, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Rot, Zahl.Fuenf)
			)
		)
		
		// when
		val entfernt = folge.wegnehmenVorne()
		
		// then
		assertThat(entfernt).isEqualTo(Stein(Farbe.Rot, Zahl.Zwei))
		
		assertThat(folge.folgeReadOnly).doesNotContain(Stein(Farbe.Rot, Zahl.Zwei))
		
		assertThat(folge.folgeReadOnly).hasSize(3)
	}
	
	@Test
	fun `wegnehmen hinten bei minimaler Folge wirft Exception`() {
		// given
		val folge = Folge(
			mutableListOf(
				Stein(Farbe.Blau, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Zwei),
				Stein(Farbe.Blau, Zahl.Drei)
			)
		)
		
		// when & then
		val exception = assertThrows<IllegalArgumentException> {
			folge.wegnehmenHinten()
		}
		
		assertThat(exception.message).contains("Mindestens 3 Steine")
	}
	
	@Test
	fun `wegnehmen vorne bei minimaler Folge wirft Exception`() {
		// given
		val folge = Folge(
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Zehn),
				Stein(Farbe.Orange, Zahl.Elf),
				Stein(Farbe.Orange, Zahl.Zwoelf)
			)
		)
		
		// when & then
		val exception = assertThrows<IllegalArgumentException> {
			folge.wegnehmenVorne()
		}
		
		assertThat(exception.message).contains("Mindestens 3 Steine")
	}
	
	@Test
	fun `hinzufuegen zu maximaler Folge wirft Exception`() {
		// given
		val folge = Folge(
			mutableListOf(
				Stein(Farbe.Schwarz, Zahl.Eins),
				Stein(Farbe.Schwarz, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Schwarz, Zahl.Vier),
				Stein(Farbe.Schwarz, Zahl.Fuenf),
				Stein(Farbe.Schwarz, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sieben),
				Stein(Farbe.Schwarz, Zahl.Acht),
				Stein(Farbe.Schwarz, Zahl.Neun),
				Stein(Farbe.Schwarz, Zahl.Zehn),
				Stein(Farbe.Schwarz, Zahl.Elf),
				Stein(Farbe.Schwarz, Zahl.Zwoelf),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			)
		)
		
		// when & then
		val exception = assertThrows<IllegalArgumentException> {
			folge.hinzufuegenHinten(Stein(Farbe.Schwarz, Zahl.Dreizehn))
		}
		
		assertThat(exception.message).contains("Maximal 13 Steine")
	}
	
	@Test
	fun `mehrfaches hinzufuegen und wegnehmen funktioniert`() {
		// given
		val folge = standardFolge()
		
		// when
		folge.hinzufuegenHinten(Stein(Farbe.Rot, Zahl.Fuenf))
		folge.hinzufuegenVorne(Stein(Farbe.Rot, Zahl.Eins))
		
		val entfernt = folge.wegnehmenHinten()
		
		folge.hinzufuegenHinten(Stein(Farbe.Rot, Zahl.Fuenf))
		
		// then
		assertThat(entfernt).isEqualTo(Stein(Farbe.Rot, Zahl.Fuenf))
		
		assertThat(folge.folgeReadOnly).containsExactly(
			Stein(Farbe.Rot, Zahl.Eins),
			Stein(Farbe.Rot, Zahl.Zwei),
			Stein(Farbe.Rot, Zahl.Drei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Rot, Zahl.Fuenf)
		)
	}
}