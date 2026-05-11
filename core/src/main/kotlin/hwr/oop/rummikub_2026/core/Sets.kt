package hwr.oop.rummikub_2026.core

//import org.assertj.core.api.Assertions.assertThat

class Sets {

    fun istGueltigesViererSet1(set: List<Stein>): Boolean {
        if (set.size !in 3..4) return false

        // Prüfen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Prüfen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
    }

    fun istGueltigesViererSet6(set: List<Stein>): Boolean {
        if (set.size !in 3..4) return false

        // Prüfen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Prüfen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
    }

    fun istGueltigesViererSet13(set: List<Stein>): Boolean {
        if (set.size !in 3..4) return false

        // Prüfen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Prüfen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
    }

    fun istGueltigesDreierSet1(set: List<Stein>): Boolean {
        if (set.size != 3) return false

        // Prüfen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Prüfen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
    }

    fun istGueltigesDreierSet6(set: List<Stein>): Boolean {
        if (set.size != 3) return false

        // Prüfen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Prüfen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
    }

    fun istGueltigesDreierSet13(set: List<Stein>): Boolean {
        if (set.size != 3) return false

        // Prüfen, ob alle die gleiche Zahl haben
        val alleZahlenGleich = set.map { it.zahl() }.toSet().size == 1

        // Prüfen, ob alle Farben unterschiedlich sind
        val alleFarbenUnterschiedlich = set.map { it.farbe() }.toSet().size == set.size

        // Die Funktion selbst entscheidet anhand der Daten!
        return alleZahlenGleich && alleFarbenUnterschiedlich
    }
}



/*             (val FolgeListe: List<Stein>): Boolean {
        assertThat(set).hasSize(4);

        val alleSindEins = set.all { it.zahl() == Zahl.Eins }
        assertThat(alleSindEins).isTrue()
        assertThat(Zahl.Eins.value).isEqualTo(1)
        assertThat(set).extracting("Farbe")
            .containsExactlyInAnyOrder(Farbe.Blau, Farbe.Orange, Farbe.Rot, Farbe.Schwarz)
    }
*/