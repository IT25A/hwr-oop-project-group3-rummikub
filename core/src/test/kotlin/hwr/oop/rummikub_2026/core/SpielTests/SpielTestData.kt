package hwr.oop.rummikub_2026.core.SpielTests

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Folge
import hwr.oop.rummikub_2026.core.Sets
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Zahl

object SpielTestData {
    val standardStein = Stein(Farbe.Blau, Zahl.Fuenf)
    val steinRot = Stein(Farbe.Rot, Zahl.Eins)
    val stein2 = Stein(Farbe.Blau, Zahl.Sechs)
    val stein3 = Stein(Farbe.Blau, Zahl.Sieben)

    fun create14Steine(vararg zusaetzlicheSteine: Stein): MutableList<Stein> {
        val steine = zusaetzlicheSteine.toMutableList()
        while (steine.size < 14) {
            steine.add(Stein(Farbe.Schwarz, Zahl.Eins))
        }
        return steine
    }

    @JvmStatic
    fun gueltigeSets3Steine() = listOf(
        Sets(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Blau, Zahl.Eins)
            )
        ),
        Sets(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Schwarz, Zahl.Sechs)
            )
        ),
        Sets(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Dreizehn),
                Stein(Farbe.Blau, Zahl.Dreizehn),
                Stein(Farbe.Schwarz, Zahl.Dreizehn)
            )
        )
    )

    @JvmStatic
    fun gueltigeSets4Steine() = listOf(
        Sets(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Blau, Zahl.Eins),
                Stein(Farbe.Schwarz, Zahl.Eins)
            )
        ),
        Sets(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Sechs),
                Stein(Farbe.Rot, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Schwarz, Zahl.Sechs)
            )
        ),
        Sets(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Dreizehn),
                Stein(Farbe.Rot, Zahl.Dreizehn),
                Stein(Farbe.Blau, Zahl.Dreizehn),
                Stein(Farbe.Schwarz, Zahl.Dreizehn)
            )
        )
    )

    @JvmStatic
    fun ungueltigeKombi() = listOf(
        Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Vier)
            )
        ),
        Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Rot, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht)
            )
        ),
        Folge(
            mutableListOf(
                Stein(Farbe.Schwarz, Zahl.Eins),
                Stein(Farbe.Schwarz, Zahl.Zwei),
                Stein(Farbe.Schwarz, Zahl.Drei),
                Stein(Farbe.Schwarz, Zahl.Vier),
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Schwarz, Zahl.Sechs),
                Stein(Farbe.Schwarz, Zahl.Sieben),
                Stein(Farbe.Schwarz, Zahl.Acht),
                Stein(Farbe.Schwarz, Zahl.Neun),
                Stein(Farbe.Schwarz, Zahl.Zehn),
                Stein(Farbe.Schwarz, Zahl.Elf),
                Stein(Farbe.Schwarz, Zahl.Zwoelf),
                Stein(Farbe.Schwarz, Zahl.Eins)
            )
        )
    )

    @JvmStatic
    fun gueltigeFolgen() = listOf(
        Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei)
            )
        ),
        Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht)
            )
        ),
        Folge(
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
    )

    @JvmStatic
    fun steine() = listOf(
        Stein(Farbe.Rot, Zahl.Eins),
        Stein(Farbe.Blau, Zahl.Sieben),
        Stein(Farbe.Schwarz, Zahl.Zwoelf),
        Stein(Farbe.Orange, Zahl.Dreizehn)
    )

    @JvmStatic
    fun gueltigeZiehenSzenarien() = listOf(
        Pair(listOf(steinRot), 15)
    )

    @JvmStatic
    fun beutelSzenarien() = listOf(
        listOf(steinRot),
        listOf(standardStein, stein2),
        listOf(steinRot, stein2, stein3, standardStein)
    )

    @JvmStatic
    fun gesammeltePunkteSzenarien() = listOf(
        Triple(
            listOf(Stein(Farbe.Blau, Zahl.Fuenf), Stein(Farbe.Blau, Zahl.Sechs), Stein(Farbe.Blau, Zahl.Sieben)),
            false,
            18
        ),
        Triple(
            listOf(Stein(Farbe.Rot, Zahl.Zehn), Stein(Farbe.Blau, Zahl.Zehn), Stein(Farbe.Schwarz, Zahl.Zehn)),
            true,
            30
        ),
        Triple(
            listOf(Stein(Farbe.Orange, Zahl.Eins), Stein(Farbe.Orange, Zahl.Zwei), Stein(Farbe.Orange, Zahl.Drei)),
            false,
            6
        )
    )

    @JvmStatic
    fun tmpListeAuslegenSzenarien() = listOf(
        Triple(
            listOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Rot, Zahl.Zwei), Stein(Farbe.Rot, Zahl.Drei)),
            false,
            true
        ),
        Triple(
            listOf(Stein(Farbe.Blau, Zahl.Fuenf), Stein(Farbe.Schwarz, Zahl.Fuenf), Stein(Farbe.Rot, Zahl.Fuenf)),
            true,
            true
        )
    )

    @JvmStatic
    fun tmpListeAuslegenMitTmpSteinenSzenarien() = listOf(
        Triple(
            listOf(Stein(Farbe.Rot, Zahl.Drei), Stein(Farbe.Rot, Zahl.Vier), Stein(Farbe.Rot, Zahl.Fuenf)),
            listOf(Stein(Farbe.Rot, Zahl.Sechs)),
            listOf(Stein(Farbe.Rot, Zahl.Sechs))
        ),
        Triple(
            listOf(Stein(Farbe.Blau, Zahl.Zehn), Stein(Farbe.Blau, Zahl.Elf), Stein(Farbe.Blau, Zahl.Zwoelf)),
            listOf(Stein(Farbe.Blau, Zahl.Dreizehn)),
            listOf(Stein(Farbe.Blau, Zahl.Dreizehn))
        ),
        Triple(
            listOf(Stein(Farbe.Schwarz, Zahl.Drei), Stein(Farbe.Schwarz, Zahl.Vier), Stein(Farbe.Schwarz, Zahl.Fuenf)),
            listOf(Stein(Farbe.Schwarz, Zahl.Vier), Stein(Farbe.Schwarz, Zahl.Fuenf)),
            emptyList()
        )
    )

    @JvmStatic
    fun tmpListeAnlegenSzenarien() = listOf(
        Pair(
            Stein(Farbe.Schwarz, Zahl.Eins),
            Folge(
                mutableListOf(
                    Stein(Farbe.Schwarz, Zahl.Zwei),
                    Stein(Farbe.Schwarz, Zahl.Drei),
                    Stein(Farbe.Schwarz, Zahl.Vier)
                )
            )
        ),
        Pair(
            Stein(Farbe.Orange, Zahl.Zehn),
            Sets(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Zehn),
                    Stein(Farbe.Blau, Zahl.Zehn),
                    Stein(Farbe.Schwarz, Zahl.Zehn)
                )
            )
        )
    )

    @JvmStatic
    fun tmpListeAnlegenMitTmpSteinenSzenarien() = listOf(
        Triple(
            Stein(Farbe.Rot, Zahl.Vier),
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Fuenf),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Rot, Zahl.Sieben)
                )
            ),
            listOf(Stein(Farbe.Blau, Zahl.Eins), Stein(Farbe.Blau, Zahl.Zwei))
        ),
        Triple(
            Stein(Farbe.Orange, Zahl.Elf),
            Sets(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Elf),
                    Stein(Farbe.Blau, Zahl.Elf),
                    Stein(Farbe.Schwarz, Zahl.Elf)
                )
            ),
            listOf(Stein(Farbe.Schwarz, Zahl.Drei))
        )
    )

    @JvmStatic
    fun ungueltigeAuslegenSzenarien() = listOf(
        Pair(
            Triple(listOf(standardStein), listOf(steinRot), arrayOf(steinRot)),
            "Du hast diesen Stein nicht!"
        ),
        Pair(
            Triple(listOf(standardStein), emptyList<Stein>(), arrayOf<Stein>()),
            "Keine Steine ausgewählt!"
        )
    )
}