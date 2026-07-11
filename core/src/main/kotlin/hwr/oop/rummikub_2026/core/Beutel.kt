package hwr.oop.rummikub_2026.core

data class Beutel(private val steineBeutel: MutableList<Stein> = mutableListOf()) {

	init {
		if (steineBeutel.isEmpty()) {
			initialisiereBeutel()
		}
	}

	val beutelReadOnly: MutableList<Stein>
		get() = steineBeutel.toMutableList()

	fun austeilen(spielerAnzahl: Int) : List<List<Stein>> {
		var ausgeteilteKarten : List<List<Stein>> = listOf()
		for (i in 1..spielerAnzahl) {
			val karten : MutableList<Stein> = mutableListOf()
			for(j in 1..14){
				karten.add(this.zieheSteinAusBeutel()!!)
			}
			ausgeteilteKarten = ausgeteilteKarten + listOf(karten)
		}
		return ausgeteilteKarten
	}

	fun initialisiereBeutel() {
		for (farbe in Farbe.entries) {
			for (zahl in Zahl.entries) {
				//jede Kombination 2x hinzufügen
				steineBeutel.add(Stein(farbe, zahl))
				steineBeutel.add(Stein(farbe, zahl))
			}
		}
		steineBeutel.shuffle()
	}

	fun zieheSteinAusBeutel(): Stein? {
		return if (steineBeutel.isNotEmpty()) {
			steineBeutel.removeAt(0)
		} else {
			null
		}
	}

	fun anzahlSteine(): Int = steineBeutel.size

	fun istLeer(): Boolean = steineBeutel.isEmpty()
}