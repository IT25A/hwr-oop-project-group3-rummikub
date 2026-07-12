package hwr.oop.rummikub_2026

import hwr.oop.rummikub_2026.core.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class SpielerTest {
	@Test
	fun `Spieler mit Namen und ID koennen existieren`() {
		val steine14 = mutableListOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Schwarz, Zahl.Drei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Orange, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Sechs),
			Stein(Farbe.Schwarz, Zahl.Sieben),
			Stein(Farbe.Orange, Zahl.Acht),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Orange, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Elf),
			Stein(Farbe.Orange, Zahl.Zwoelf),
			Stein(Farbe.Blau, Zahl.Dreizehn),
			Stein(Farbe.Schwarz, Zahl.Dreizehn)
		)
		
		val spieler1 = Spieler(
			"Thanh",
			SpielerId("1"),
			steine14
		)
		
		val spieler2 = Spieler(
			"Gabriela",
			SpielerId("2"),
			steine14
		)
		
		val spieler3 = Spieler(
			"Charlotte",
			SpielerId("3"),
			steine14
		)
		
		val spieler4 = Spieler(
			"Maxi-Taxi",
			SpielerId("4"),
			steine14
		)
		
		assertThat(spieler1.nameReadOnly).isEqualTo("Thanh")
		
		assertThat(spieler2.nameReadOnly).isEqualTo("Gabriela")
		
		assertThat(spieler3.nameReadOnly).isEqualTo("Charlotte")
		
		assertThat(spieler4.nameReadOnly).isEqualTo("Maxi-Taxi")
	}
	
	@Test
	fun `Stein ziehen funktioniert`() {
		val steine14 = mutableListOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Schwarz, Zahl.Drei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Orange, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Sechs),
			Stein(Farbe.Schwarz, Zahl.Sieben),
			Stein(Farbe.Orange, Zahl.Acht),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Orange, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Elf),
			Stein(Farbe.Orange, Zahl.Zwoelf),
			Stein(Farbe.Blau, Zahl.Dreizehn),
			Stein(Farbe.Schwarz, Zahl.Dreizehn)
		)
		
		val spieler = Spieler(
			"Luxi-Taxi",
			SpielerId("1"),
			steine14
		)
		
		spieler.ziehen(Stein(Farbe.Orange, Zahl.Zwei))
		
		assertThat(spieler.brettReadOnly).contains(Stein(Farbe.Orange, Zahl.Zwei))
	}
	
	companion object {
		@JvmStatic
		fun liste14Steine() = listOf(
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Orange, Zahl.Fuenf),
				Stein(Farbe.Blau, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sieben),
				Stein(Farbe.Orange, Zahl.Acht),
				Stein(Farbe.Blau, Zahl.Neun),
				Stein(Farbe.Orange, Zahl.Zehn),
				Stein(Farbe.Rot, Zahl.Elf),
				Stein(Farbe.Orange, Zahl.Zwoelf),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			)
		)
		
		@JvmStatic
		fun listeNicht14Steine() = listOf(
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Orange, Zahl.Fuenf),
				Stein(Farbe.Blau, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sieben),
				Stein(Farbe.Orange, Zahl.Acht),
				Stein(Farbe.Blau, Zahl.Neun),
				Stein(Farbe.Orange, Zahl.Zehn),
				Stein(Farbe.Rot, Zahl.Elf),
				Stein(Farbe.Orange, Zahl.Zwoelf),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Blau, Zahl.Dreizehn)
			),
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier),
				Stein(Farbe.Orange, Zahl.Fuenf),
				Stein(Farbe.Blau, Zahl.Sechs),
				Stein(Farbe.Schwarz, Zahl.Sieben),
				Stein(Farbe.Orange, Zahl.Acht),
				Stein(Farbe.Blau, Zahl.Neun),
				Stein(Farbe.Orange, Zahl.Zehn),
				Stein(Farbe.Rot, Zahl.Elf),
				Stein(Farbe.Orange, Zahl.Zwoelf),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Blau, Zahl.Dreizehn),
				Stein(Farbe.Schwarz, Zahl.Dreizehn)
			),
			mutableListOf(
				Stein(Farbe.Orange, Zahl.Eins),
				Stein(Farbe.Blau, Zahl.Zwei),
				Stein(Farbe.Schwarz, Zahl.Drei),
				Stein(Farbe.Rot, Zahl.Vier)
			),
			mutableListOf()
		)
		
		@JvmStatic
		fun listeGueltigeSteine() = listOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Schwarz, Zahl.Dreizehn)
		)
		
		@JvmStatic
		fun listeUngueltigeSteine() = listOf(
			Stein(Farbe.Orange, Zahl.Zwei),
			Stein(Farbe.Blau, Zahl.Eins),
			Stein(Farbe.Schwarz, Zahl.Sechs)
		)
		
		@JvmStatic
		fun listeSpielerIds() = listOf(
			"1",
			"2",
			"spieler-123",
			"player-abc"
		)
	}
	
	@ParameterizedTest
	@MethodSource("listeGueltigeSteine")
	fun `Stein loeschen funktioniert`(
		stein: Stein,
	) {
		val loeschenListe = mutableListOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Orange, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Sechs),
			Stein(Farbe.Schwarz, Zahl.Sieben),
			Stein(Farbe.Orange, Zahl.Acht),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Orange, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Elf),
			Stein(Farbe.Orange, Zahl.Zwoelf),
			Stein(Farbe.Blau, Zahl.Dreizehn),
			Stein(Farbe.Schwarz, Zahl.Dreizehn)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("4"),
			loeschenListe.toMutableList()
		)
		
		spieler.entfernen(stein)
		loeschenListe.remove(stein)
		
		assertThat(spieler.brettReadOnly).containsExactlyInAnyOrderElementsOf(loeschenListe)
	}
	
	@ParameterizedTest
	@MethodSource("listeUngueltigeSteine")
	fun `Stein, der nicht in der Liste ist, loeschen funktioniert nicht`(
		stein: Stein,
	) {
		val loeschenListe = mutableListOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Orange, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Sechs),
			Stein(Farbe.Schwarz, Zahl.Sieben),
			Stein(Farbe.Orange, Zahl.Acht),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Orange, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Elf),
			Stein(Farbe.Orange, Zahl.Zwoelf),
			Stein(Farbe.Blau, Zahl.Dreizehn),
			Stein(Farbe.Schwarz, Zahl.Dreizehn)
		)
		
		val spieler = Spieler(
			"Maxi-Taxi",
			SpielerId("4"),
			loeschenListe.toMutableList()
		)
		
		val exception = assertThrows<IllegalArgumentException> {
			spieler.entfernen(stein)
		}
		
		assertThat(exception.message).contains("Der Stein ist nicht vorhanden.")
	}
	
	@ParameterizedTest
	@MethodSource("liste14Steine")
	fun `Anfangssteine werfen keine Exception`(
		anfangsSteine: MutableList<Stein>,
	) {
		Assertions.assertDoesNotThrow {
			val spieler1 = Spieler(
				"Thanh",
				SpielerId("1"),
				anfangsSteine
			)
		}
	}
	
	@ParameterizedTest
	@MethodSource("listeNicht14Steine")
	fun `Ungueltige Anzahl Anfangssteine werfen Exception`(
		anfangsSteine: MutableList<Stein>,
	) {
		val exception = assertThrows<IllegalArgumentException> {
			val spieler1 = Spieler(
				"Thanh",
				SpielerId("1"),
				anfangsSteine
			)
		}
		
		assertThat(exception.message).contains("Es muss 14 Steine vergeben werden")
	}
	
	@ParameterizedTest
	@MethodSource("listeSpielerIds")
	fun `Spieler ID wird korrekt gesetzt`(
		spielerId: String,
	) {
		val steine14 = mutableListOf(
			Stein(Farbe.Orange, Zahl.Eins),
			Stein(Farbe.Blau, Zahl.Zwei),
			Stein(Farbe.Schwarz, Zahl.Drei),
			Stein(Farbe.Rot, Zahl.Vier),
			Stein(Farbe.Orange, Zahl.Fuenf),
			Stein(Farbe.Blau, Zahl.Sechs),
			Stein(Farbe.Schwarz, Zahl.Sieben),
			Stein(Farbe.Orange, Zahl.Acht),
			Stein(Farbe.Blau, Zahl.Neun),
			Stein(Farbe.Orange, Zahl.Zehn),
			Stein(Farbe.Rot, Zahl.Elf),
			Stein(Farbe.Orange, Zahl.Zwoelf),
			Stein(Farbe.Blau, Zahl.Dreizehn),
			Stein(Farbe.Schwarz, Zahl.Dreizehn)
		)
		
		val spieler = Spieler(
			"TestSpieler",
			SpielerId(spielerId),
			steine14
		)
		
		assertThat(spieler.id.value).isEqualTo(spielerId)
	}
}