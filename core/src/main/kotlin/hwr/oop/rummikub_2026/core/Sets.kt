package hwr.oop.rummikub_2026.core

data class Sets(private val setListe: MutableList<Stein>) : Kombinationen {
	val setReadOnly: List<Stein>
		get() = setListe.toList()

	override fun get(): MutableList<Stein> {
		return setListe.toMutableList()
	}

	override fun istGueltig() {
		require(setListe.size >= 3) { "Mindestens 3 Steine" }
		require(setListe.size <= 4) { "Maximal 4 Steine" }
		require(alleZahlenGleich()) { "Alle Steine muessen dieselbe Zahl haben" }
		require(alleFarbenUnterschiedlich()) { "Alle Steine muessen unterschiedliche Farbe haben" }
	}

	private fun alleZahlenGleich(): Boolean {
		//Alle gleiche Zahl
		val zahlen = setListe[0].zahl()
		for (i in setListe) {
			if (i.zahl() != zahlen) {
				return false
			}
		}
		return true
	}

	private fun alleFarbenUnterschiedlich(): Boolean {
		//Alle unterschiedliche Farbe
		val farben = mutableSetOf<Any>()
		for (i in setListe) {
			if (!farben.add(i.farbe())) {
				return false
			}
		}
		return true
	}

	fun hinzufuegenZumSet(
        stein: Stein,
	) {
		setListe.add(stein)
		this.istGueltig()
	}

	fun wegnehmenVomSet(
        stein: Stein,
	): Stein {
		setListe.remove(stein)
		this.istGueltig()
		return stein
	}
}