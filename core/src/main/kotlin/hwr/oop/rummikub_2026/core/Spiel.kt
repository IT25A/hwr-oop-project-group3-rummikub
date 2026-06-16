package hwr.oop.rummikub_2026.core

import java.io.InvalidObjectException

data class Spiel(
    val aktivSpieler: Spieler,
    val beutel: List<Stein>,
    val tisch: Tisch
) {
    var gesammeltePunkte: Int = 0

    fun gueltigerZug() {
        tisch.gueltig()
        gesammeltePunkte = 0
    }

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

        for (i in liste) {
            gesammeltePunkte += i.zahl().value

        }

        if(gesammeltePunkte >= 30){
            aktivSpieler.rausgekommen = true
        }

        mglSteine = mglSteine - liste
        aktuellerTisch.tmpListe = mglSteine.intersect(aktuellerTisch.tmpListe).toMutableList()
        brett = (mglSteine - aktuellerTisch.tmpListe).toMutableList()

        return this.copy(
            tisch = aktuellerTisch,
            aktivSpieler = Spieler(aktivSpieler.nameReadOnly, aktivSpieler.id, brett, aktivSpieler.rausgekommen, false)
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

        require(spieler.rausgekommen) { "Du musst erst rauskommen, bevor du anlegen kannst!" }

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
            aktivSpieler = Spieler(aktivSpieler.nameReadOnly, aktivSpieler.id, brett, aktivSpieler.rausgekommen, false)
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
            aktivSpieler = Spieler(
                aktivSpieler.nameReadOnly,
                aktivSpieler.id,
                neueSteine,
                aktivSpieler.rausgekommen,
                false
            )
        )
    }

    fun aufloesen(
        aufzuloesendeKombi: Kombinationen,
        spieler: Spieler,
        aktuellerTisch: Tisch
    ): Spiel {
        require(spieler.rausgekommen) { "Du musst erst rauskommen, bevor du anlegen kannst!" }
        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }
        var brett = aktivSpieler.boardReadOnly

        if (aufzuloesendeKombi.get().isEmpty()) {
            throw InvalidObjectException("Keine Kombi ausgewählt!")
        }

        tisch.aufloesen(aufzuloesendeKombi)

        return this.copy(
            tisch = aktuellerTisch,
            aktivSpieler = Spieler(aktivSpieler.nameReadOnly, aktivSpieler.id, brett, aktivSpieler.rausgekommen, false)
        )
    }
}
