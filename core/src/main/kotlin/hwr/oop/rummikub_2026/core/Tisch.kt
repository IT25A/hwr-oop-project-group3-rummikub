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

    fun kombiLegen (
        istSet: Boolean, liste: MutableList<Stein>
    ) {
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
    
    fun anlegen (
        kombination: Int, stein: Stein
    ) {
        if (kombination >= tisch.size) {
            throw IndexOutOfBoundsException("Die Kombi gibt es nicht")
        }
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
    fun aufloesen (
    kombi: Kombinationen
    ){
        val stelle = tisch.indexOf(kombi)
        require(stelle != -1){"Ungueltiger Zug: Diese Kombination gibt es nicht."}
        tmpListe.addAll(tisch[stelle].get())
        tisch.removeAt(stelle)
    }
}