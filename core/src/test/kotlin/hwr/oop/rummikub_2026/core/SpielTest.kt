package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import java.io.InvalidObjectException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.Arguments

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

    @ParameterizedTest
    @MethodSource("ziehenTestCases")
    fun `Spieler zieht Stein - verschiedene Szenarien`(
        testName: String,
        aktiverSpieler: String,
        ziehenderSpieler: String,
        beutelGroesse: Int,
        sollFehlerWerfen: Boolean,
        erwarteteExceptionMessage: String?,
        erwarteteBrettGroesse: Int?,
        erwarteteBeutelGroesse: Int?
    ) {
        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val spieler2 = Spieler("Smilla", "2", steine14.toMutableList())

        val aktivSpieler = if (aktiverSpieler == "spieler1") spieler1 else spieler2
        val ziehenderSpieler = if (ziehenderSpieler == "spieler1") spieler1 else spieler2

        val startBeutel = if (beutelGroesse == 0) emptyList()
                          else listOf(steinImBeutel1, steinImBeutel2).take(beutelGroesse)

        val startBretter = mapOf(
            spieler1 to spieler1.boardReadOnly,
            spieler2 to spieler2.boardReadOnly
        )

        val spiel = Spiel(
            aktivSpieler = aktivSpieler,
            beutel = startBeutel,
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        if (sollFehlerWerfen) {
            val exception = assertThrows<Exception> {
                spiel.ziehen(ziehenderSpieler)
            }
            if (erwarteteExceptionMessage != null) {
                assertThat(exception.message).isEqualTo(erwarteteExceptionMessage)
            }
        } else {
            val neuesSpiel = spiel.ziehen(ziehenderSpieler)
            val endBrettDesSpielers = neuesSpiel.spielerBretter[ziehenderSpieler]!!

            assertThat(endBrettDesSpielers).hasSize(erwarteteBrettGroesse!!)
            assertThat(endBrettDesSpielers).contains(steinImBeutel1)
            assertThat(neuesSpiel.beutel).hasSize(erwarteteBeutelGroesse!!)
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("legenFehlerTestCases")
    fun `Spieler legt Steine - Fehlerszenarien`(
        testName: String,
        spielerBoard: List<Stein>,
        kombination: Folge,
        ausgespielteSteine: List<Stein>,
        aktiverSpieler: String,
        legenderSpieler: String,
        erwarteteExceptionMessage: String
    ) {
        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())
        val spieler2 = Spieler("Smilla", "2", steine14.toMutableList())

        val aktivSpieler = if (aktiverSpieler == "spieler1") spieler1 else spieler2
        val legenderSpieler = if (legenderSpieler == "spieler1") spieler1 else spieler2

        val startBretter = mapOf(
            spieler1 to spielerBoard,
            spieler2 to spieler2.boardReadOnly
        )

        val spiel = Spiel(
            aktivSpieler = aktivSpieler,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = emptyList()
        )

        val exception = assertThrows<InvalidObjectException> {
            spiel.legen(
                legenderSpieler,
                kombination,
                ausgespielteSteine
            )
        }
        assertThat(exception.message).isEqualTo(erwarteteExceptionMessage)
    }

    @ParameterizedTest
    @MethodSource("legenErfolgTestCases")
    fun `Spieler legt Steine - Erfolgsszenarien`(
        testName: String,
        spielerBoard: List<Stein>,
        kombination: Kombinationen,
        ausgespielteSteine: List<Stein>,
        alteKombination: Kombinationen?,
        erwarteteSteineAnzahl: Int,
        erwarteteBrettGroesse: Int
    ) {
        val spieler1 = Spieler("Luxi-Taxi", "1", steine14.toMutableList())

        val startBretter = mapOf(spieler1 to spielerBoard)

        val spiel = Spiel(
            aktivSpieler = spieler1,
            beutel = emptyList(),
            spielerBretter = startBretter,
            alleSteine = if (alteKombination != null) listOf(alteKombination) else emptyList()
        )

        val neuesSpiel = spiel.legen(
            spieler1,
            kombination,
            ausgespielteSteine,
            alteKombination
        )

        assertThat(neuesSpiel.alleSteine).hasSize(erwarteteSteineAnzahl)
        assertThat(neuesSpiel.alleSteine).contains(kombination)
        if (alteKombination != null) {
            assertThat(neuesSpiel.alleSteine).doesNotContain(alteKombination)
        }
        assertThat(neuesSpiel.spielerBretter[spieler1]).hasSize(erwarteteBrettGroesse)
    }

    companion object {
        @JvmStatic
        fun ziehenTestCases() = listOf(
            Arguments.of(
                "ungueltiger Spieler darf nicht ziehen",
                "spieler1",
                "spieler2",
                0,
                true,
                null,
                null,
                null
            ),
            Arguments.of(
                "gueltiger Spieler zieht einen Stein aus dem Beutel",
                "spieler1",
                "spieler1",
                2,
                false,
                null,
                15,
                1
            ),
            Arguments.of(
                "Spieler darf nicht ziehen wenn der Beutel leer ist",
                "spieler1",
                "spieler1",
                0,
                true,
                "Der Beutel ist leer!",
                null,
                null
            )
        )

        @JvmStatic
        fun legenFehlerTestCases() = listOf(
            Arguments.of(
                "Spieler spielt nicht vorhandenen Stein",
                listOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Acht)
                ),
                Folge(
                    mutableListOf(
                        Stein(Farbe.Blau, Zahl.Fuenf),
                        Stein(Farbe.Blau, Zahl.Sechs),
                        Stein(Farbe.Blau, Zahl.Sieben)
                    )
                ),
                listOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben)
                ),
                "spieler1",
                "spieler1",
                "Du hast diesen Stein nicht!"
            ),
            Arguments.of(
                "Spieler legt nichts",
                listOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Acht)
                ),
                Folge(mutableListOf()),
                emptyList<Stein>(),
                "spieler1",
                "spieler1",
                "Keine Steine ausgewählt!"
            ),
            Arguments.of(
                "ungueltiger Spieler darf nicht legen",
                listOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Acht)
                ),
                Folge(
                    mutableListOf(
                        Stein(Farbe.Blau, Zahl.Fuenf),
                        Stein(Farbe.Blau, Zahl.Sechs),
                        Stein(Farbe.Blau, Zahl.Sieben)
                    )
                ),
                emptyList<Stein>(),
                "spieler1",
                "spieler2",
                "Spieler ist nicht an der Reihe!"
            )
        )

        @JvmStatic
        fun legenErfolgTestCases(): List<Arguments> {
            val stein1 = Stein(Farbe.Blau, Zahl.Fuenf)
            val stein2 = Stein(Farbe.Blau, Zahl.Sechs)
            val stein3 = Stein(Farbe.Blau, Zahl.Sieben)

            val stein1Rot = Stein(Farbe.Rot, Zahl.Fuenf)
            val stein2Rot = Stein(Farbe.Rot, Zahl.Sechs)
            val stein3Rot = Stein(Farbe.Rot, Zahl.Sieben)
            val neuerSteinRot = Stein(Farbe.Rot, Zahl.Acht)

            return listOf(
                Arguments.of(
                    "Spieler legt neue gueltige Folge",
                    listOf(stein1, stein2, stein3),
                    Folge(mutableListOf(stein1, stein2, stein3)),
                    listOf(stein1, stein2, stein3),
                    null,
                    1,
                    0
                ),
                Arguments.of(
                    "Spieler legt Stein an bestehende Kombination an",
                    listOf(neuerSteinRot),
                    Folge(mutableListOf(stein1Rot, stein2Rot, stein3Rot, neuerSteinRot)),
                    listOf(neuerSteinRot),
                    Folge(mutableListOf(stein1Rot, stein2Rot, stein3Rot)),
                    1,
                    0
                )
            )
        }
    }
}