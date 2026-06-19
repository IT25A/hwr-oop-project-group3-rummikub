package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class TischTest {
    companion object {
        @JvmStatic
        fun kombinationen() = listOf(
            Sets(mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins)
            )),
            Sets(mutableListOf(
                Stein(Farbe.Rot, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Schwarz, Zahl.Sieben)
            )),
            Folge(mutableListOf(
                Stein(Farbe.Orange, Zahl.Acht),
                Stein(Farbe.Orange, Zahl.Neun),
                Stein(Farbe.Orange, Zahl.Zehn)
            )),
            Folge(mutableListOf(
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht),
                Stein(Farbe.Blau, Zahl.Neun),
                Stein(Farbe.Blau, Zahl.Zehn),
                Stein(Farbe.Blau, Zahl.Elf)
            ))
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
            // Boundary test: position equals tisch.size (should throw)
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
                1  // tisch has 1 element (index 0), so position 1 equals tisch.size and should throw
            ),
            // Another boundary test with larger list
            Pair(
                listOf(
                    Folge(mutableListOf(Stein(Farbe.Rot, Zahl.Eins), Stein(Farbe.Rot, Zahl.Zwei), Stein(Farbe.Rot, Zahl.Drei))),
                    Folge(mutableListOf(Stein(Farbe.Blau, Zahl.Eins), Stein(Farbe.Blau, Zahl.Zwei), Stein(Farbe.Blau, Zahl.Drei))),
                    Folge(mutableListOf(Stein(Farbe.Schwarz, Zahl.Eins), Stein(Farbe.Schwarz, Zahl.Zwei), Stein(Farbe.Schwarz, Zahl.Drei)))
                ),
                3  // tisch has 3 elements, so position 3 equals tisch.size
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
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
    fun `Sets und Folgen sind gueltig im Tisch`(
        kombi: Kombinationen
    ) {
        // given
        val testTisch = Tisch(mutableListOf(kombi))

        // then
        assertDoesNotThrow {
            testTisch.gueltig()
        }
    }
    @ParameterizedTest
    @MethodSource("ungueltigeKombi")
    fun `Sets und Folgen sind nicht gueltig im Tisch`(
        kombi: Kombinationen
    ) {
        // given
        val testTisch = Tisch(mutableListOf(kombi))

        // then
        assertThrows<IllegalArgumentException> {
            testTisch.gueltig()
        }
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
    fun `Folgen und Sets koennen auf dem Tisch liegen`(
        kombi: Kombinationen
    ) {
        // given
        val testTisch = Tisch(mutableListOf(kombi))

        // then
        assertThat(testTisch.tischReadOnly).contains(kombi)
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeSteineFuerFolgen")
    fun `kombi legen fuer Folge pruefen`(
        liste: MutableList<Stein>
    ) {
        // given
        val tisch = Tisch(mutableListOf())

        // when
        tisch.kombiLegen(false, liste)

        // then
        assertThat(tisch.tischReadOnly).isNotEmpty()
    }
    @ParameterizedTest
    @MethodSource("gueltigeSteineFuerSets")
    fun `kombi legen fuer Sets pruefen`(
        liste: MutableList<Stein>
    ) {
        // given
        val tisch = Tisch(mutableListOf())

        // when
        tisch.kombiLegen(true, liste)

        // then
        assertThat(tisch.tischReadOnly).isNotEmpty()
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeSets3Steine", "gueltigeSets4Steine")
    fun `anlegen funktioniert set`(
        set: Sets
    ) {
        // given
        val tisch = Tisch(mutableListOf(set))

        // when
        tisch.anlegen(0, Stein(Farbe.Orange, Zahl.Eins))

        // then
        assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen")
    fun `anlegen funktioniert folge`(
        folge: Folge
    ) {
        // given
        val tisch = Tisch(mutableListOf(folge))

        // when
        tisch.anlegen(0, Stein(Farbe.Orange, Zahl.Eins))

        // then
        assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
    }
    @ParameterizedTest
    @MethodSource("kombinationen")
    fun `alle Steine einer Kombination werden in tmpListe uebernommen und Kombination wird vom Tisch geloescht`(
        kombination: Kombinationen
    ) {
        //given
        val tisch = Tisch(
            mutableListOf(kombination)
        )
        val tischZuvor = tisch.tischReadOnly
        val tmpListeLaenge = tisch.tmpListe.size
        tisch.aufloesen(kombination)

        assertEquals(
            kombination.get(),
            tisch.tmpListe
        )
        assertEquals(
            tischZuvor - tisch.tischReadOnly,
            listOf(kombination)
        )
        assertEquals(
            tisch.tmpListe.size,
            tmpListeLaenge + kombination.get().size
        )
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2])
    fun `Kombination an beliebiger Position kann aufgeloest werden`(
        index: Int
    ) {
        // given
        val k1 = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        val k2 = Sets(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Schwarz, Zahl.Sieben),
                Stein(Farbe.Orange, Zahl.Sieben)
            )
        )

        val k3 = Folge(
            mutableListOf(
                Stein(Farbe.Orange, Zahl.Zehn),
                Stein(Farbe.Orange, Zahl.Elf),
                Stein(Farbe.Orange, Zahl.Zwoelf)
            )
        )

        val kombis = mutableListOf(k1, k2, k3)
        val target = kombis[index]
        val tisch = Tisch(kombis)

        // when
        tisch.aufloesen(target)

        // then
        assertFalse(tisch.tischReadOnly.contains(target))
    }
    @Test
    fun `Exception wenn Kombination nicht auf dem Tisch liegt`() {
        // given
        val vorhandene = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei)
            )
        )

        val nichtVorhandene = Folge(
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Elf),
                Stein(Farbe.Blau, Zahl.Zwoelf),
                Stein(Farbe.Blau, Zahl.Dreizehn)
            )
        )

        val tisch = Tisch(mutableListOf(vorhandene))

        // then
        assertThrows<IllegalArgumentException> {
            tisch.aufloesen(nichtVorhandene)
        }
    }
    
    @Test
    fun `bei zwei identischen Folgen bleibt eine erhalten`() {
        // given
        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        val tisch = Tisch(mutableListOf(folge, folge))

        // when
        tisch.aufloesen(folge)

        // then
        assertEquals(1, tisch.tischReadOnly.size)
        assertTrue(tisch.tischReadOnly.contains(folge))
    }

    @ParameterizedTest
    @MethodSource("ungueltigeSteineFuerSets")
    fun `kombi legen fuer ungueltige Sets wirft Exception`(
        liste: MutableList<Stein>
    ) {
        // given
        val tisch = Tisch(mutableListOf())

        // then
        assertThrows<IllegalArgumentException> {
            tisch.kombiLegen(true, liste)
        }
    }

    @ParameterizedTest
    @MethodSource("ungueltigeSteineFuerFolgen")
    fun `kombi legen fuer ungueltige Folgen wirft Exception`(
        liste: MutableList<Stein>
    ) {
        // given
        val tisch = Tisch(mutableListOf())

        // then
        assertThrows<IllegalArgumentException> {
            tisch.kombiLegen(false, liste)
        }
    }

    @ParameterizedTest
    @MethodSource("anlegenAusserhalb")
    fun `anlegen ausserhalb der Tischgroesse wirft Exception`(
        testfall: Pair<List<Kombinationen>, Int>
    ) {
        // given
        val kombis = testfall.first
        val position = testfall.second
        val tisch = Tisch(kombis.toMutableList())

        // then
        val exception = assertThrows<IndexOutOfBoundsException> {
            tisch.anlegen(position, Stein(Farbe.Rot, Zahl.Eins))
        }
        assertThat(exception.message).isEqualTo("Die Kombi gibt es nicht")
    }

    @ParameterizedTest
    @MethodSource("anlegenGrenzfallSzenarien")
    fun `anlegen auf der letzten Position funktioniert`(
        testfall: Triple<List<Kombinationen>, Int, Stein>
    ) {
        // given
        val kombis = testfall.first
        val position = testfall.second
        val stein = testfall.third
        val tisch = Tisch(kombis.toMutableList())

        // when & then
        assertDoesNotThrow {
            tisch.anlegen(position, stein)
        }

        assertThat(tisch.tischReadOnly[position].get()).contains(stein)
    }

    @ParameterizedTest
    @MethodSource("anlegenSetsSzenarien")
    fun `anlegen an Sets erkennt Sets korrekt`(
        testfall: Pair<Sets, Stein>
    ) {
        // given
        val set = testfall.first
        val stein = testfall.second
        val tisch = Tisch(mutableListOf(set))

        // when
        tisch.anlegen(0, stein)

        // then
        assertThat(tisch.tischReadOnly[0]).isInstanceOf(Sets::class.java)
        assertThat(tisch.tischReadOnly[0].get()).contains(stein)
    }

    @ParameterizedTest
    @MethodSource("anlegenFolgenSzenarien")
    fun `anlegen an Folge erkennt Folge korrekt`(
        testfall: Pair<Folge, Stein>
    ) {
        // given
        val folge = testfall.first
        val stein = testfall.second
        val tisch = Tisch(mutableListOf(folge))

        // when
        tisch.anlegen(0, stein)

        // then
        assertThat(tisch.tischReadOnly[0]).isInstanceOf(Folge::class.java)
        assertThat(tisch.tischReadOnly[0].get()).contains(stein)
    }
}