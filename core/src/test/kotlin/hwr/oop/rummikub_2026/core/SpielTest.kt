package hwr.oop.rummikub_2026.core

import hwr.oop.rummikub_2026.core.Kombinationen
import hwr.oop.rummikub_2026.core.Stein
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.InvalidObjectException
import org.junit.jupiter.api.assertThrows
import kotlin.collections.List

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

        val startBretter = mapOf(
            spieler1 to spieler1.boardReadOnly,
            spieler2 to spieler2.boardReadOnly
        )

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = emptyList()
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

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = startBeutel,
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

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
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        val exception = assertThrows<IllegalStateException> {
            spiel.ziehen(spieler1)
        }
        assertThat(exception.message).isEqualTo("Der Beutel ist leer!")
    }
    @Test
    fun `Spieler spielt nicht vorhandenen Stein`(){

        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val startBretter = mapOf(
            spieler1 to listOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Acht),
            )
        )
        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        val kombination = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben)
            )
        )

        val exception = assertThrows<InvalidObjectException> {
            spiel.legen(
                spieler1,
                kombination,
                listOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben)  // <- Hat er nicht
                )
            )
        }
        assertThat(exception.message).isEqualTo("Du hast diesen Stein nicht!")
    }

    @Test
    fun `Spieler legt nichts`(){
        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val startBretter = mapOf(
            spieler1 to listOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Acht),
            )
        )
        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        val kombination = Folge(
            mutableListOf(
            )
        )


        val exception = assertThrows<InvalidObjectException> {
            spiel.legen(
                spieler1,
                kombination,
                listOf()
            )
        }
        assertThat(exception.message).isEqualTo("Keine Steine ausgewählt!")
    }

    @Test
    fun `ungueltiger Spieler darf nicht legen`() {

        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val spieler2 = Spieler("Smilla", "2", steine14.toMutableList())

        val startBretter = mapOf(
            spieler1 to spieler1.boardReadOnly,
            spieler2 to spieler2.boardReadOnly
        )

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        val kombination = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben)
            )
        )

        val exception = assertThrows<InvalidObjectException> {
            spiel.legen(
                spieler2,
                kombination,
                listOf()
            )
        }
        assertThat(exception.message).isEqualTo("Spieler ist nicht an der Reihe!")
    }

    @Test
    fun `Spieler legt neue gueltige Folge`() {
        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val stein1 = Stein(Farbe.Blau, Zahl.Fuenf)
        val stein2 = Stein(Farbe.Blau, Zahl.Sechs)
        val stein3 = Stein(Farbe.Blau, Zahl.Sieben)

        val startBretter = mapOf(
            spieler1 to listOf(stein1, stein2, stein3)
        )

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        val kombination = Folge(
            mutableListOf(stein1, stein2, stein3)
        )

        val neuesSpiel = spiel.legen(
            spieler1,
            kombination,
            listOf(stein1, stein2, stein3)
        )

        assertThat(neuesSpiel.alleSteine).hasSize(1)
        assertThat(neuesSpiel.alleSteine).contains(kombination)
        assertThat(neuesSpiel.spielerBretter[spieler1]).isEmpty()
    }

    @Test
    fun `Spieler legt Stein an bestehende Kombination an`() {
        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val stein1 = Stein(Farbe.Rot, Zahl.Fuenf)
        val stein2 = Stein(Farbe.Rot, Zahl.Sechs)
        val stein3 = Stein(Farbe.Rot, Zahl.Sieben)
        val neuerStein = Stein(Farbe.Rot, Zahl.Acht)

        val alteKombination = Folge(
            mutableListOf(stein1, stein2, stein3)
        )

        val startBretter = mapOf(
            spieler1 to listOf(neuerStein)
        )

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = listOf(alteKombination)
        )

        val neueKombination = Folge(
            mutableListOf(stein1, stein2, stein3, neuerStein)
        )

        val neuesSpiel = spiel.legen(
            spieler1,
            neueKombination,
            listOf(neuerStein),
            alteKombination
        )

        assertThat(neuesSpiel.alleSteine).hasSize(1)
        assertThat(neuesSpiel.alleSteine).contains(neueKombination)
        assertThat(neuesSpiel.alleSteine).doesNotContain(alteKombination)
        assertThat(neuesSpiel.spielerBretter[spieler1]).isEmpty()
    }
}