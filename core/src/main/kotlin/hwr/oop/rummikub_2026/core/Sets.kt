package hwr.oop.rummikub_2026.core

//import org.assertj.core.api.Assertions.assertThat

class Sets {
//besser: exceptions mit message
    fun istGueltigesSet(set: List<Stein>): Boolean {
        if (set.size !in 3..4) return false

        // Pruefen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Pruefen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
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