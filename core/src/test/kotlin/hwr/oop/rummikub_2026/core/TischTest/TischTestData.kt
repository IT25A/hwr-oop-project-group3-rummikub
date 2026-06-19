package hwr.oop.rummikub_2026.core.TischTest

import hwr.oop.rummikub_2026.core.Farbe
import hwr.oop.rummikub_2026.core.Folge
import hwr.oop.rummikub_2026.core.Sets
import hwr.oop.rummikub_2026.core.Stein
import hwr.oop.rummikub_2026.core.Zahl

object TischTestData {
    @JvmStatic
    fun kombinationen() = listOf(
        Sets(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins)
            )
        ),
        Sets(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Schwarz, Zahl.Sieben)
            )
        ),
        Folge(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Acht),
                Stein(Farbe.Orange, Zahl.Neun),
                Stein(Farbe.Orange, Zahl.Zehn)
            )
        ),
        Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun),
                Stein(Farbe.Blau, Zahl.Zehn),
                Stein(Farbe.Blau, Zahl.Elf)
            )
        )
    )

    @JvmStatic
    fun gueltigeSteineFuerSets() = listOf(
        mutableListOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Schwarz, Zahl.Eins),
            Stein(Farbe.Blau, Zahl.Eins)
        ),
        mutableListOf(
            Stein(Farbe.Blau, Zahl.Fuenf),
            Stein(Farbe.Orange, Zahl.Fuenf),
            Stein(Farbe.Schwarz, Zahl.Fuenf),
            Stein(Farbe.Rot, Zahl.Fuenf)
        )
    )

    @JvmStatic
    fun gueltigeSteineFuerFolgen() = listOf(
        mutableListOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Zwei),
            Stein(Farbe.Rot, Zahl.Drei)
        ),
        mutableListOf(
            Stein(Farbe.Blau, Zahl.Fuenf),
            Stein(Farbe.Blau, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sieben),
            Stein(Farbe.Blau, Zahl.Acht)
        ),
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
    fun ungueltigeSteineFuerSets() = listOf(
        mutableListOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Eins)
        ),
        mutableListOf(
            Stein(Farbe.Blau, Zahl.Fuenf),
            Stein(Farbe.Blau, Zahl.Sechs)
        )
    )

    @JvmStatic
    fun ungueltigeSteineFuerFolgen() = listOf(
        mutableListOf(
            Stein(Farbe.Rot, Zahl.Eins),
            Stein(Farbe.Rot, Zahl.Drei)
        ),
        mutableListOf(
            Stein(Farbe.Blau, Zahl.Fuenf),
            Stein(Farbe.Rot, Zahl.Sechs),
            Stein(Farbe.Blau, Zahl.Sieben)
        )
    )

    @JvmStatic
    fun anlegenGrenzfallSzenarien() = listOf(
        Triple(
            listOf(
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
                        Stein(Farbe.Blau, Zahl.Sieben)
                    )
                )
            ),
            1,
            Stein(Farbe.Blau, Zahl.Acht)
        ),
        Triple(
            listOf(
                Sets(
                    mutableListOf(
                        Stein(Farbe.Rot, Zahl.Zehn),
                        Stein(Farbe.Blau, Zahl.Zehn),
                        Stein(Farbe.Schwarz, Zahl.Zehn)
                    )
                ),
                Folge(
                    mutableListOf(
                        Stein(Farbe.Orange, Zahl.Eins),
                        Stein(Farbe.Orange, Zahl.Zwei),
                        Stein(Farbe.Orange, Zahl.Drei)
                    )
                )
            ),
            1,
            Stein(Farbe.Orange, Zahl.Vier)
        )
    )

    @JvmStatic
    fun anlegenAusserhalb() = listOf(
        Pair(
            listOf(
                Folge(
                    mutableListOf(
                        Stein(Farbe.Rot, Zahl.Eins),
                        Stein(Farbe.Rot, Zahl.Zwei),
                        Stein(Farbe.Rot, Zahl.Drei)
                    )
                )
            ),
            2
        ),
        Pair(
            listOf(
                Sets(
                    mutableListOf(
                        Stein(Farbe.Rot, Zahl.Fuenf),
                        Stein(Farbe.Blau, Zahl.Fuenf),
                        Stein(Farbe.Schwarz, Zahl.Fuenf)
                    )
                ),
                Folge(
                    mutableListOf(
                        Stein(Farbe.Orange, Zahl.Zehn),
                        Stein(Farbe.Orange, Zahl.Elf),
                        Stein(Farbe.Orange, Zahl.Zwoelf)
                    )
                )
            ),
            3
        ),
        Pair(
            listOf(
                Folge(
                    mutableListOf(
                        Stein(Farbe.Blau, Zahl.Eins),
                        Stein(Farbe.Blau, Zahl.Zwei),
                        Stein(Farbe.Blau, Zahl.Drei)
                    )
                )
            ),
            1
        ),
        Pair(
            listOf(
                Folge(
                    mutableListOf(
                        Stein(Farbe.Rot, Zahl.Eins),
                        Stein(Farbe.Rot, Zahl.Zwei),
                        Stein(Farbe.Rot, Zahl.Drei)
                    )
                ),
                Folge(
                    mutableListOf(
                        Stein(Farbe.Blau, Zahl.Eins),
                        Stein(Farbe.Blau, Zahl.Zwei),
                        Stein(Farbe.Blau, Zahl.Drei)
                    )
                ),
                Folge(
                    mutableListOf(
                        Stein(Farbe.Schwarz, Zahl.Eins),
                        Stein(Farbe.Schwarz, Zahl.Zwei),
                        Stein(Farbe.Schwarz, Zahl.Drei)
                    )
                )
            ),
            3
        )
    )

    @JvmStatic
    fun anlegenSetsSzenarien() = listOf(
        Pair(
            Sets(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Schwarz, Zahl.Fuenf)
                )
            ),
            Stein(Farbe.Orange, Zahl.Fuenf)
        ),
        Pair(
            Sets(
                mutableListOf(
                    Stein(Farbe.Orange, Zahl.Dreizehn),
                    Stein(Farbe.Rot, Zahl.Dreizehn),
                    Stein(Farbe.Blau, Zahl.Dreizehn)
                )
            ),
            Stein(Farbe.Schwarz, Zahl.Dreizehn)
        )
    )

    @JvmStatic
    fun anlegenFolgenSzenarien() = listOf(
        Pair(
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Zwei),
                    Stein(Farbe.Rot, Zahl.Drei)
                )
            ),
            Stein(Farbe.Rot, Zahl.Vier)
        ),
        Pair(
            Folge(
                mutableListOf(
                    Stein(Farbe.Blau, Zahl.Elf),
                    Stein(Farbe.Blau, Zahl.Zwoelf),
                    Stein(Farbe.Blau, Zahl.Dreizehn)
                )
            ),
            Stein(Farbe.Blau, Zahl.Zehn)
        )
    )
}