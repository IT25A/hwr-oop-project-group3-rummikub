package hwr.oop.rummikub_2026.core

class Tisch (private val tisch: MutableList<Kombinationen>) {
    val tischReadOnly: List<Kombinationen>
        get() = tisch.toList()

    init {
        val copyTisch = tisch.toMutableList()
    }

    var tmpListe = mutableListOf<Stein>()

    fun gueltig(){
        for (i in tisch){
            i.istGueltig()
            require(tmpListe.isEmpty()){"Ungueltiger Zug, du hast nicht alle aufgeloesten Kombinationen verwendet."}
        }
    }

    fun kombiLegen (istSet: Boolean, liste: MutableList<Stein>) {
        val mglKombi : Kombinationen

        if (istSet){
            mglKombi = Sets(liste)
            mglKombi.istGueltig()
        }else {
            mglKombi = Folge(liste)
            mglKombi.istGueltig()
        }
        tisch.add(mglKombi)
    }
    
    fun anlegen (kombination: Int, stein: Stein) {
        require(tisch.size >= kombination) { "Die Kombi gibt es nicht" }
        if (tisch[kombination] is Sets) {
            val neueListe = tisch[kombination].get()
            neueListe.add(stein)
            val neuesSet = Sets(neueListe)
            tisch[kombination] = neuesSet
        }else{
            val neueListe = tisch[kombination].get()
            neueListe.add(stein)
            val neueFolge = Folge(neueListe)
            tisch[kombination] = neueFolge
        }
    }

//    fun aufloesen (kombi: Int){
//        tmpListe.addAll(tisch[kombi].get())
//        tisch.removeAt(kombi)
//    }
    fun aufloesen (kombi: Kombinationen){
        val stelle = tisch.indexOf(kombi)
        require(stelle != -1){"Ungueltiger Zug: Diese Kombination gibt es nicht."}
        tmpListe.addAll(tisch[stelle].get())
        tisch.removeAt(stelle)
    }
}

/* (+)TODO: Tisch mit einer Liste mit Folgen und Sets (interface)
   (+)TODO: neue kombi legen
   (+)TODO: Tisch/Kombis anzeige
   (+)TODO: etwas anlegen können
   (+)TODO: gucken ob alles valid ist
    TODO: auseinanderziehen
    TODO: temporäre Liste aktualisieren nach jedem Zug

   */

/*In Kotlin gibt es die Möglichkeit, eine Funktion so zu definieren, dass sie eine variable Anzahl an Argumenten mit demselben Datentyp entgegennehmen kann.
Dies nennt man ein Vararg-Parameter. Dies ist nützlich, wenn man nicht genau weiß, wie viele Argumente an die Funktion übergeben werden. 
Ein Vararg-Parameter wird in der Funktionsdeklaration mit dem Schlüsselwort vararg gekennzeichnet.*/