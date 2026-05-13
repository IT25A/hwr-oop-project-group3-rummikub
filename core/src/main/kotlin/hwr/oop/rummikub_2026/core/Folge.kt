package hwr.oop.rummikub_2026.core

class Folge(private val folgeListe: MutableList<Stein>) {

    val folgeReadOnly: List<Stein>
        get() = folgeListe.toList()

    fun isValid(){
        require(folgeListe.size >= 3){"Mindestens 3 Steine"}
        require(folgeListe.size <= 13){"Maximal 13 Steine"}
        require(alleGleicheFarbe()){"Alle Steine muessen die selbe Farbe haben"}
        require(indexverschiebung()){"Steine muessen aufeinander Folgen"}
    }

    private fun alleGleicheFarbe(): Boolean {
        //Alle selbe Farbe
        val ersteFarbe = folgeListe[0].farbe()
        for (i in folgeListe) {
            if (i.farbe() != ersteFarbe) {
                return false  }
        }
      return true
    }

    private fun indexverschiebung(): Boolean {
        for (i in 0 until folgeListe.size - 1) {
            val aktuellerWert = folgeListe[i].zahl().value
            val naechsterWert = folgeListe[i + 1].zahl().value
            if (naechsterWert != aktuellerWert + 1) {
                return false
            }
        }
        return true
    }
//Stefan fragen try catch
    fun hinzufuegenHinten(stein: Stein) {
        folgeListe.add(stein)

        try {//aendern
            this.isValid()
        } catch (e: IllegalArgumentException) {
            folgeListe.removeLast()
            throw e
        }
    }

    fun hinzufuegenVorne(stein: Stein) {
        folgeListe.add(0, stein)
            this.isValid()

       /* try {
        } catch (e: IllegalArgumentException) {
            folgeListe.removeFirst()
            throw e
        }*/
        /*
        * Idee zur Vermeidung von try-catch:
        * 
        *  val neueListe = folgeListe + stein
        * require(istValid(neueListe))
        * folgeListe.add(stein)
        * 
        * man muesste isValid anpassen, sodass man jede Liste als Parameter uebergeben kann
        * */
    }

}

