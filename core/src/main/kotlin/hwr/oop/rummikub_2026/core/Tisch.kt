package hwr.oop.rummikub_2026.core

class Tisch (private val tisch: MutableList<Kombinationen>) {
    val tischReadOnly: List<Kombinationen>
        get() = tisch.toList()

    fun kombiLegen (istSet: Boolean, vararg steine: Stein): Kombinationen {
        val mglKombi : Kombinationen
        val liste: MutableList<Stein> = mutableListOf()
        for(stein in steine) {
            liste.add  (stein)
        }

        if (istSet){
            mglKombi = Sets(liste)
            mglKombi.istGueltig()
        }else {
            mglKombi = Folge(liste)
            mglKombi.istGueltig()
        }
		return mglKombi;
    }
}

/* (+)TODO: Tisch mit einer Liste mit Folgen und Sets (interface)
   (+)TODO: neue kombi legen


    TODO: Tisch/Kombis anzeige
    TODO: etwas anlegen können
    TODO: auseinanderziehen
    TODO: temporäre Liste aktualisieren nach jedem Zug
    TODO: gucken ob alles valid ist
   */

/*In Kotlin gibt es die Möglichkeit, eine Funktion so zu definieren, dass sie eine variable Anzahl an Argumenten mit demselben Datentyp entgegennehmen kann.
Dies nennt man ein Vararg-Parameter. Dies ist nützlich, wenn man nicht genau weiß, wie viele Argumente an die Funktion übergeben werden. 
Ein Vararg-Parameter wird in der Funktionsdeklaration mit dem Schlüsselwort vararg gekennzeichnet.*/