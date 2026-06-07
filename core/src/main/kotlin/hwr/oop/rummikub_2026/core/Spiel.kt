package hwr.oop.rummikub_2026.core

import java.io.InvalidObjectException

data class Spiel(
    val aktivSpieler: Spieler,
    val beutel: List<Stein>,
    val spielerBretter:
    Map<Spieler, List<Stein>>
) {
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
}