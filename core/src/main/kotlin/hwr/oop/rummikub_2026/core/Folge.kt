package hwr.oop.rummikub_2026.core

data class Folge(private val folgeListe: MutableList<Stein>) : Kombinationen {
	val folgeReadOnly: List<Stein>
		get() = folgeListe.toMutableList()
	
	override fun get(): MutableList<Stein> {
		return folgeListe.toMutableList()
	}
	
	override fun istGueltig() {
		require(folgeListe.size >= 3) { "Mindestens 3 Steine" }
		require(folgeListe.size <= 13) { "Maximal 13 Steine" }
		require(alleGleicheFarbe()) { "Alle Steine muessen die selbe Farbe haben" }
		require(indexverschiebung()) { "Steine muessen aufeinander Folgen" }
	}
	
	private fun alleGleicheFarbe(): Boolean {
		val ersteFarbe = folgeListe
			.firstOrNull { it.farbe() != Farbe.Joker }
			?.farbe()
			?: return false
		
		return folgeListe.all {
			it.farbe() == Farbe.Joker || it.farbe() == ersteFarbe
		}
	}
	
	private fun jokerWert(reihenfolge: MutableList<Stein>): Zahl {
		if (reihenfolge[1].farbe() == Farbe.Joker) {
			val wert = jokerWert((reihenfolge - reihenfolge[0]).toMutableList())
			
			val neuerWert = Zahl.entries.first {
				it.value == reihenfolge[1].zahl().value - 1
			}
			
			reihenfolge[0].jokerWertAnpassen(neuerWert)
			return neuerWert
		}
		
		val neuerWert = Zahl.entries.firstOrNull {
			it.value == reihenfolge[1].zahl().value - 1
		} ?: throw IllegalArgumentException("Joker kann keinen Wert erhalten")
		
		reihenfolge[0].jokerWertAnpassen(neuerWert)
		return neuerWert
	}
	
	private fun indexverschiebung(): Boolean {
		if (folgeListe[0].farbe() == Farbe.Joker) {
			jokerWert(folgeListe)
		}
		
		for (i in 0 until folgeListe.size - 1) {
			val aktuellerWert = folgeListe[i].zahl().value
			val naechsterWert = folgeListe[i + 1].zahl().value
			
			if (naechsterWert != aktuellerWert + 1) {
				if (folgeListe[i + 1].farbe() == Farbe.Joker) {
					val jokerWert = Zahl.entries.firstOrNull {
						it.value == aktuellerWert + 1
					} ?: return false
					
					folgeListe[i + 1].jokerWertAnpassen(jokerWert)
				} else {
					return false
				}
			}
		}
		
		return true
	}
	
	fun hinzufuegenHinten(
		stein: Stein,
	) {
		folgeListe.add(stein)
		this.istGueltig()
	}
	
	fun hinzufuegenVorne(
		stein: Stein,
	) {
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