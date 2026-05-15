package hwr.oop.rummikub_2026.core

import kotlin.collections.get
import kotlin.compareTo

//import org.assertj.core.api.Assertions.assertThat

class Sets(private val setListe: MutableList<Stein>) {

    val setReadOnly: List<Stein>
        get() = setListe.toList()

    fun istGueltigesSet(){
        require(alleZahlenGleich()){"Alle Steine muessen dieselbe Zahl haben"}
        require(setListe.size >= 3){"Mindestens 3 Steine"}
        require(setListe.size <= 4){"Maximal 4 Steine"}
        require(alleFarbenUnterschiedlich()){"Alle Steine muessen unterschiedliche Farbe haben"}
         }

    private fun alleZahlenGleich(): Boolean {
        //Alle gleiche Zahl
        val zahlen = setListe[0].zahl()
        for (i in setListe) {
            if (i.zahl() != zahlen) {
                //Line Coverage hier nicht
                return false  }
        }
        return true
    }

    private fun alleFarbenUnterschiedlich(): Boolean {
        //Alle unterschiedliche Farbe
        val farben = mutableSetOf<Any>()
        for (i in setListe) {
            if (!farben.add(i.farbe())) {
                return false  }
        }

        //Line Coverage hier nicht
        return true
    }

}

/*             (val folgeListe: List<Stein>): Boolean {
        assertThat(set).hasSize(4);

        val alleSindEins = set.all { it.zahl() == Zahl.Eins }
        assertThat(alleSindEins).isTrue()
        assertThat(Zahl.Eins.value).isEqualTo(1)
        assertThat(set).extracting("Farbe")
            .containsExactlyInAnyOrder(Farbe.Blau, Farbe.Orange, Farbe.Rot, Farbe.Schwarz)
    }
*/