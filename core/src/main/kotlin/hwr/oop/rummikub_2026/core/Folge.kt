package hwr.oop.rummikub_2026.core

data class Folge(private val folgeListe: MutableList<Stein>) : Kombinationen {
	
	val folgeReadOnly: List<Stein>
		get() = folgeListe.toMutableList()
	
	override fun get() : MutableList<Stein>{
		return folgeListe.toMutableList()
	}
	override fun istGueltig() {
		require(folgeListe.size >= 3) { "Mindestens 3 Steine" }
		require(folgeListe.size <= 13) { "Maximal 13 Steine" }
		require(alleGleicheFarbe()) { "Alle Steine muessen die selbe Farbe haben" }
		require(indexverschiebung()) { "Steine muessen aufeinander Folgen" }
	}
	
	private fun alleGleicheFarbe(): Boolean {
		//Alle selbe Farbe
		val ersteFarbe = folgeListe[0].farbe()
		for (i in folgeListe) {
			if (i.farbe() != ersteFarbe) {
				return false
			}
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
	
	fun hinzufuegenHinten(stein: Stein) {
		folgeListe.add(stein)
		this.istGueltig()
	}
	
	fun hinzufuegenVorne(stein: Stein) {
		folgeListe.add(0, stein)
		this.istGueltig()
	}
	
	fun wegnehmenVorne(): Stein {
		val stein = folgeListe[0]
		folgeListe.removeFirst()
		this.istGueltig()
		return stein
	}
	
	fun wegnehmenHinten(): Stein {
		val stein = folgeListe[folgeListe.size - 1]
		folgeListe.removeLast()
		this.istGueltig()
		return stein
	}
}