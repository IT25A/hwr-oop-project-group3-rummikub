package hwr.oop.rummikub_2026.core

import java.io.InvalidObjectException


data class Spiel(
    val aktivSpieler: Spieler,
    val beutel: List<Stein>,
    val spielerBretter: Map<Spieler, List<Stein>>,
    val tisch: Tisch
)
{

    fun zug(spieler: Spieler): Spiel {

    }

    fun ziehen(spieler: Spieler): Spiel {

        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }
        if (beutel.isEmpty()) {
            throw IllegalStateException("Der Beutel ist leer!")
        }
        val gezogenerStein = beutel.first()
        val neuerBeutel = beutel - gezogenerStein

        val aktuelleSteine = spielerBretter[aktivSpieler] ?: aktivSpieler.boardReadOnly
        val neueSteine = aktuelleSteine + gezogenerStein

        val neueBretterMap = spielerBretter + (aktivSpieler to neueSteine)

        return this.copy(
            beutel = neuerBeutel,
            spielerBretter = neueBretterMap
        )
    }

    fun legen(spieler: Spieler,
              kombination: Kombinationen,
              neuStein: List<Stein>,
              alteKombination: Kombinationen? = null
    ): Spiel {

        // Spielspezifische Prüfungen (bleiben im Spiel)
        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        val brett = spielerBretter[spieler]!!
        if (!brett.containsAll(neuStein)) {
            throw InvalidObjectException("Du hast diesen Stein nicht!")
        }

        if (neuStein.isEmpty()) {
            throw InvalidObjectException("Keine Steine ausgewählt!")
        }

        val aktuelleTischListe = tisch.tischReadOnly.toMutableList()
        val neuerTisch = Tisch(aktuelleTischListe)


//        // Validierung durch Kombination selbst
//        kombination.istGueltig()
//
//        // Erstelle neue Tisch-Liste (Tisch-Manager verwaltet die Kombinationen)
//        val aktuelleTischListe = tisch.tischReadOnly.toMutableList()
//        if (alteKombination != null) {
//            // Entferne alte Kombination und füge neue hinzu
//            aktuelleTischListe.remove(alteKombination)
//        }
//        aktuelleTischListe.add(kombination)
//
//        val neuerTisch = Tisch(aktuelleTischListe)
//        val neuBrett = spielerBretter + (spieler to (brett - neuStein.toSet()))
//
//        return this.copy(
//            tisch = neuerTisch,
//            spielerBretter = neuBrett
//        )
    }
}
