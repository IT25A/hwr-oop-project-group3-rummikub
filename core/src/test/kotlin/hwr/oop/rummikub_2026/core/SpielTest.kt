package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.InvalidObjectException
import org.junit.jupiter.api.assertThrows

class SpielTest {

    private val steine14 = mutableListOf(
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

    private val steinImBeutel1 = Stein(Farbe.Rot, Zahl.Eins)
    private val steinImBeutel2 = Stein(Farbe.Blau, Zahl.Zwei)

    @Test
    fun `ungueltiger Spieler darf nicht ziehen`() {

        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val spieler2 = Spieler("Smilla", "2", steine14.toMutableList())

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = emptyMap()
        )


        assertThrows<InvalidObjectException> {
            spiel.ziehen(spieler2)
        }
    }

    @Test
    fun `gueltiger Spieler zieht einen Stein aus dem Beutel`() {

        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val startBeutel = listOf(steinImBeutel1, steinImBeutel2)


        val startBretter = mapOf(spieler1 to spieler1.boardReadOnly)

        val spiel = Spiel(aktivSpieler = spieler1, beutel = startBeutel, spielerBretter = startBretter)


        val neuesSpiel = spiel.ziehen(spieler1)



        val endBrettDesSpielers = neuesSpiel.spielerBretter[spieler1]!!


        assertThat(endBrettDesSpielers).hasSize(15)
        assertThat(endBrettDesSpielers).contains(steinImBeutel1)
        assertThat(neuesSpiel.beutel).hasSize(1)
    }

    @Test
    fun `Spieler darf nicht ziehen wenn der Beutel leer ist`() {

        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val startBretter = mapOf(spieler1 to spieler1.boardReadOnly)


        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter
        )


        val exception = assertThrows<IllegalStateException> {
            spiel.ziehen(spieler1)
        }
        assertThat(exception.message).isEqualTo("Der Beutel ist leer!")
    }
}