package hwr.oop.rummikub_2026.core

class Folge(val FolgeListe: MutableList<Stein>) {
 init{

 }
    fun isValid() : Boolean {
        require(FolgeListe.size >= 3){"Mindestens 3 Steine"}
        require(FolgeListe.size <= 13){"Maximal 13 Steine"}
        require(alleGleicheFarbe()){"Alle Steine müssen die selbe Farbe haben"}
        require(indexverschiebung()){"Steine müssen aufeinander Folgen"}
        return true
    }

    private fun alleGleicheFarbe(): Boolean {
        //Alle selbe Farbe
        val ersteFarbe = FolgeListe[0].farbe()
        for (i in FolgeListe) {
            if (i.farbe() != ersteFarbe) {
                return false  }
        }
      return true
    }

    private fun indexverschiebung(): Boolean {
        for (i in 0 until FolgeListe.size - 1) {
            val aktuellerWert = FolgeListe[i].zahl().value
            val naechsterWert = FolgeListe[i + 1].zahl().value
            if (naechsterWert != aktuellerWert + 1) {
                return false
            }
        }
        return true
    }
//Stefan fragen try catch
    fun hinzufuegenHinten(stein: Stein) {
        FolgeListe.add(stein)

        try {
            this.isValid()
        } catch (e: IllegalArgumentException) {
            FolgeListe.removeLast()
            throw e
        }
    }

    fun hinzufuegenVorne(stein: Stein) {
        FolgeListe.add(0, stein)

        try {
            this.isValid()
        } catch (e: IllegalArgumentException) {
            FolgeListe.removeFirst()
            throw e
        }
    }

}

