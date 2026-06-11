package hwr.oop.rummikub_2026.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TischTest {
    companion object {
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
            // Minimum
            mutableListOf(
                Stein(Farbe.Rot, Zahl.Eins),
                Stein(Farbe.Rot, Zahl.Zwei),
                Stein(Farbe.Rot, Zahl.Drei)
            ),
            
            // Mittelfall
            mutableListOf(
                Stein(Farbe.Blau, Zahl.Fuenf),
                Stein(Farbe.Blau, Zahl.Sechs),
                Stein(Farbe.Blau, Zahl.Sieben),
                Stein(Farbe.Blau, Zahl.Acht)
            ),
            
            // Maximum
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
            // Minimum
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Zwei),
                    Stein(Farbe.Rot, Zahl.Drei)
                )
            ),
            
            // Mittelfall
            Folge(
                mutableListOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Blau, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben),
                    Stein(Farbe.Blau, Zahl.Acht)
                )
            ),
            
            // Maximum
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
            // Minimum
            Folge(
                mutableListOf(
                    Stein(Farbe.Rot, Zahl.Eins),
                    Stein(Farbe.Rot, Zahl.Zwei),
                    Stein(Farbe.Rot, Zahl.Vier)
                )
            ),
            
            // Mittelfall
            Folge(
                mutableListOf(
                    Stein(Farbe.Blau, Zahl.Fuenf),
                    Stein(Farbe.Rot, Zahl.Sechs),
                    Stein(Farbe.Blau, Zahl.Sieben),
                    Stein(Farbe.Blau, Zahl.Acht)
                )
            ),
            
            // Maximum
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
        val test = tisch.kombiLegen(false, *liste.toTypedArray())
        //*list.toIntArray()
        
        assertThat(tisch.tischReadOnly).isNotEmpty()
    }
    @ParameterizedTest
    @MethodSource("gueltigeSteineFuerSets")
    fun `kombi legen fuer Sets pruefen`(
        liste: MutableList<Stein>
    ) {
        val tisch = Tisch(mutableListOf())
        val test = tisch.kombiLegen(true, *liste.toTypedArray())
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
        //*list.toIntArray()
        
        assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
    }
    
    @ParameterizedTest
    @MethodSource("gueltigeFolgen")
    fun `anlegen funktioniert folge`(
        folge: Folge
    ) {
        val tisch = Tisch(mutableListOf(folge))
        tisch.anlegen(0, Stein(Farbe.Orange, Zahl.Eins))
        //*list.toIntArray()
        
        assertThat(tisch.tischReadOnly[0].get()).contains(Stein(Farbe.Orange, Zahl.Eins))
    }

    @ParameterizedTest
    @MethodSource("gueltigeFolgen")
    fun `aufloesen funktioniert folge`(
        folge: Folge
    ) {
        val tisch = Tisch(mutableListOf(folge))
        tisch.aufloesen(0)
        //*list.toIntArray()
        assertThat(tisch.tmpListe).isEqualTo(folge.get())
    }
}