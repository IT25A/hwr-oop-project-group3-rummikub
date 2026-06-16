package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
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
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
    fun `Sets und Folgen sind gueltig im Tisch`(
        kombi: Kombinationen
    ) {
        val testTisch = Tisch(mutableListOf(kombi))
        
        Assertions.assertDoesNotThrow {
            testTisch.gueltig()
        }
    }
    @ParameterizedTest
    @MethodSource("ungueltigeKombi")
    fun `Sets und Folgen sind nicht gueltig im Tisch`(
        kombi: Kombinationen
    ) {
        val testTisch = Tisch(mutableListOf(kombi))
        
        assertThrows<IllegalArgumentException> {
            testTisch.gueltig()
        }
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen", "gueltigeSets4Steine", "gueltigeSets3Steine")
    fun `Folgen und Sets koennen auf dem Tisch liegen`(
        kombi: Kombinationen
    ) {
        val testTisch = Tisch(mutableListOf(kombi))
        
        assertThat(testTisch.tischReadOnly).contains(kombi)
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeSteineFuerFolgen")
    fun `kombi legen fuer Folge pruefen`(
        liste: MutableList<Stein>
    ) {
        val tisch = Tisch(mutableListOf())
        val test = tisch.kombiLegen(false, liste)
        //*list.toIntArray()
        
        assertThat(tisch.tischReadOnly).isNotEmpty()
    }
    @ParameterizedTest
    @MethodSource("gueltigeSteineFuerSets")
    fun `kombi legen fuer Sets pruefen`(
        liste: MutableList<Stein>
    ) {
        val tisch = Tisch(mutableListOf())
        val test = tisch.kombiLegen(true, liste)
        //*list.toIntArray()
        
        assertThat(tisch.tischReadOnly).isNotEmpty()
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeSets3Steine", "gueltigeSets4Steine")
    fun `anlegen funktioniert set`(
        set: Sets
    ) {
        val tisch = Tisch(mutableListOf(set))
        tisch.anlegen(0, Stein(Farbe.Orange, Zahl.Eins))
        
        assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen")
    fun `anlegen funktioniert folge`(
        folge: Folge
    ) {
        val tisch = Tisch(mutableListOf(folge))
        tisch.anlegen(0, Stein(Farbe.Orange, Zahl.Eins))
        
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

        val kombis = mutableListOf(
            k1,
            k2,
            k3
        )

        val target = kombis[index]

        val tisch = Tisch(kombis)

        tisch.aufloesen(target)

        assertFalse(tisch.tischReadOnly.contains(target))
    }
    @Test
    fun `Exception wenn Kombination nicht auf dem Tisch liegt`() {
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

        assertThrows<IllegalArgumentException> {
            tisch.aufloesen(nichtVorhandene)
        }
    }
    
    @Test
    fun `bei zwei identischen Folgen bleibt eine erhalten`() {

        val folge = Folge(
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Drei),
                Stein(Farbe.Rot, Zahl.Vier),
                Stein(Farbe.Rot, Zahl.Fuenf)
            )
        )

        val tisch = Tisch(
            mutableListOf(
                folge,
                folge
            )
        )

        tisch.aufloesen(folge)

        assertEquals(1, tisch.tischReadOnly.size)

        assertTrue(tisch.tischReadOnly.contains(folge))
    }
}