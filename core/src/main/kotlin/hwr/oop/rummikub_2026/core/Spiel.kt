package hwr.oop.rummikub_2026.core

import kotlinx.serialization.Serializable
import java.io.InvalidObjectException

@Serializable
data class Spiel(
    val aktivSpieler: Spieler,
    val beutel: List<Stein>,
    val tisch: Tisch,
    val spieler: List<Spieler>,
    val gesammeltePunkte: Int = 0,
    val id: SpielId = SpielId.random()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Spiel) return false

        return aktivSpieler == other.aktivSpieler &&
                beutel == other.beutel &&
                tisch.tischReadOnly == other.tisch.tischReadOnly &&
                id.wert == other.id.wert
    }

    fun gueltigerZug():Spiel{
        tisch.gueltig()
        //if(aktivSpieler.brettReadOnly.isEmpty()){
        //gewonnen(aktivSpieler)
        //}
        return this.copy(
            gesammeltePunkte = 0
        )
    }

//    fun gewonnen(
//        gewinner: Spieler
//    ) {
//        val spieler = mutableListOf<Spieler>(
//            Spieler("Maxi-Taxi", "2", mutableListOf(Stein(Farbe.Blau, Zahl.Zehn), Stein(Farbe.Rot, Zahl.Eins)), true, false),
//            Spieler("Luxi-Taxi", "3", mutableListOf(Stein(Farbe.Blau, Zahl.Drei), Stein(Farbe.Schwarz, Zahl.Zwei)),true, false),
//            Spieler("Fuxi-Taxi", "1", mutableListOf(),true, false)
//        )
//
//        val rangliste = mutableListOf<Spieler>(gewinner)
//        spieler -= gewinner
//    }

    fun auslegen(
        spieler: Spieler,
        istSet: Boolean,
        steine: List<Stein>,
    ): Spiel {
        if (steine.isEmpty()) {
            throw InvalidObjectException("Keine Steine ausgewählt!")
        }

        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        var brett = aktivSpieler.brettReadOnly
        var moeglicheSteine = brett + tisch.tmpListe
        if (!moeglicheSteine.containsAll(steine)) {
            throw InvalidObjectException("Du hast diesen Stein nicht!")
        }

        tisch.kombiLegen(istSet, steine.toMutableList())

        var neuGesammeltePunkte = gesammeltePunkte
        for (stein in steine) {
            neuGesammeltePunkte += stein.zahl().value
        }
        if (neuGesammeltePunkte >= 30) {
            aktivSpieler.rausgekommen = true
        }

        moeglicheSteine = moeglicheSteine - steine
        tisch.tmpListe = moeglicheSteine.intersect(tisch.tmpListe).toMutableList()
        brett = (moeglicheSteine - tisch.tmpListe).toMutableList()

        return this.copy(
            aktivSpieler = Spieler(
                aktivSpieler.nameReadOnly,
                aktivSpieler.id,
                brett,
                aktivSpieler.rausgekommen,
                false
            ),
            gesammeltePunkte = neuGesammeltePunkte,
		)
    }

    fun anlegen(
        spieler: Spieler,
        neuStein: Stein,
        kombi: Int,
        aktuellerTisch: Tisch,
    ): Spiel {
        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        require(spieler.rausgekommen) { "Du musst erst rauskommen, bevor du anlegen kannst!" }

        var brett = aktivSpieler.brettReadOnly
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
            aktivSpieler = Spieler(
                aktivSpieler.nameReadOnly,
                aktivSpieler.id,
                brett,
                aktivSpieler.rausgekommen,
                false
            )
        )
    }

    fun ziehen(
        spieler: Spieler,
    ): Spiel {
        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        if (beutel.isEmpty()) {
            throw IllegalStateException("Der Beutel ist leer!")
        }

        val gezogenerStein = beutel.first()
        val neuerBeutel = beutel - gezogenerStein

        val aktuelleSteine = (aktivSpieler.brettReadOnly)
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
        aktuellerTisch: Tisch,
    ): Spiel {
        require(spieler.rausgekommen) { "Du musst erst rauskommen, bevor du aufloesen kannst!" }

        if (spieler != aktivSpieler) {
            throw InvalidObjectException("Spieler ist nicht an der Reihe!")
        }

        var brett = aktivSpieler.brettReadOnly

        if (aufzuloesendeKombi.get().isEmpty()) {
            throw InvalidObjectException("Keine Kombi ausgewählt!")
        }

        tisch.aufloesen(aufzuloesendeKombi)

        return this.copy(
            tisch = aktuellerTisch,
            aktivSpieler = Spieler(
                aktivSpieler.nameReadOnly,
                aktivSpieler.id,
                brett,
                aktivSpieler.rausgekommen,
                false
            )
        )
    }
}