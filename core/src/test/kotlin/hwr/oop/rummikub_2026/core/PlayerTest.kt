package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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
        //given
        val spieler1 = Spieler("Thanh", "1", steine14)
        val spieler2 = Spieler("Gabriela", "2", steine14)
        val spieler3 = Spieler("Charlotte", "3", steine14)
        val spieler4 = Spieler("Maxi-Taxi", "4", steine14)
        //when

        //then
        assertThat(spieler1.nameReadOnly == "Thanh")
        assertThat(spieler2.nameReadOnly == "Gabriela")
        assertThat(spieler3.nameReadOnly == "Charlotte")
        assertThat(spieler4.nameReadOnly == "Maxi-Taxi")
    }

    @Test
    fun `Stein ziehen funktioniert`() {
        //given
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
        val spieler = Spieler("Luxi-Taxi", "1", steine14)
        //when
        spieler.ziehen(Stein(Farbe.Orange, Zahl.Zwei));
        //then
        assertThat(spieler.boardReadOnly).contains(Stein(Farbe.Orange, Zahl.Zwei))
    }

    companion object {
        @JvmStatic
        fun liste14Steine() = listOf(
            // 14 Steine
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
            // 14 Steine
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
    }

    @ParameterizedTest
    @MethodSource("liste14Steine")
    fun ` Anfangssteine werfen keine Exception`(
        anfangsSteine: MutableList<Stein>
    ) {
        //then
        Assertions.assertDoesNotThrow {
            val spieler1 = Spieler("Thanh", "1", anfangsSteine)
        }
    }

    @ParameterizedTest
    @MethodSource("listeNicht14Steine")
    fun `Ungueltige Anzahl Anfangssteine werfen Exception`(
        anfangsSteine: MutableList<Stein>
    ) {
        //then
        val exception = assertThrows<IllegalArgumentException> {
            val spieler1 = Spieler("Thanh", "1", anfangsSteine)
        }
        assertThat(exception.message).contains("Es muss 14 Steine vergeben werden")
    }
}


//Spieler kann Steine weglegen->Steine werden aus der Liste entfernt
//get() für board.readOnly für Steinestand
