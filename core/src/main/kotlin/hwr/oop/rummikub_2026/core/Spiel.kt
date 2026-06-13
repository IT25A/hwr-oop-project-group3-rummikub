package hwr.oop.rummikub_2026.core

import java.io.InvalidObjectException

data class Spiel(
    val aktivSpieler: Spieler,
    val beutel: List<Stein>,
    val tisch: Tisch
) {
    fun auslegen(
        spieler: Spieler,
        istSet: Boolean,
        //neuStein: List<Stein>,
        aktuellerTisch: Tisch,
        vararg steine: Stein
    ): Spiel {

        val liste: MutableList<Stein> = mutableListOf()
        for (stein in steine) {
            liste.add(stein)
        }

        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        var brett = aktivSpieler.boardReadOnly
        var mglSteine = brett + aktuellerTisch.tmpListe

        if (!(mglSteine).containsAll(liste)) {
            throw InvalidObjectException("Du hast diesen Stein nicht!")
        }

        if (liste.isEmpty()) {
            throw InvalidObjectException("Keine Steine ausgewählt!")
        }

        aktuellerTisch.kombiLegen(istSet, liste)

        mglSteine = mglSteine - liste
        aktuellerTisch.tmpListe = mglSteine.intersect(aktuellerTisch.tmpListe).toMutableList()
        brett = (mglSteine - aktuellerTisch.tmpListe).toMutableList()

        return this.copy(
            tisch = aktuellerTisch,
            aktivSpieler = Spieler(aktivSpieler.nameReadOnly, aktivSpieler.id, brett, validateInitialCount = false)
        )
    }

    fun anlegen(
        spieler: Spieler,
        neuStein: Stein,
        kombi: Int,
        aktuellerTisch: Tisch
    ): Spiel {

        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        var brett = aktivSpieler.boardReadOnly
        var mglSteine = brett + aktuellerTisch.tmpListe

        if (!(mglSteine).contains(neuStein)) {
            throw InvalidObjectException("Du hast diesen Stein nicht!")
        }

        aktuellerTisch.anlegen(kombi, neuStein)

        mglSteine = mglSteine - neuStein
        aktuellerTisch.tmpListe = mglSteine.intersect(aktuellerTisch.tmpListe).toMutableList()
        brett = (mglSteine - aktuellerTisch.tmpListe).toMutableList()

        return this.copy(
            tisch = aktuellerTisch,
            aktivSpieler = Spieler(aktivSpieler.nameReadOnly, aktivSpieler.id, brett, validateInitialCount = false)
        )
    }

//    if (alteKombination != null) {
//    // Entferne alte Kombination und füge neue hinzu
//    aktuelleTischListe.remove(alteKombination)
//}
//    aktuelleTischListe.add(kombination)

    fun ziehen(spieler: Spieler): Spiel {

        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        if (beutel.isEmpty()) {
            throw IllegalStateException("Der Beutel ist leer!")
        }

        val gezogenerStein = beutel.first()
        val neuerBeutel = beutel - gezogenerStein

        val aktuelleSteine = (aktivSpieler.boardReadOnly)
        val neueSteine = (aktuelleSteine + gezogenerStein).toMutableList()

        return this.copy(
            beutel = neuerBeutel,
            aktivSpieler = Spieler(aktivSpieler.nameReadOnly, aktivSpieler.id, neueSteine, validateInitialCount = false)
        )
    }
}
