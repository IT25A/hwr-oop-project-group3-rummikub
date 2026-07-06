package hwr.oop.rummikub_2026.core
import kotlinx.serialization.Serializable
@Serializable
data class Spieler(
    private val name: String,
    val id: String,
    private val brett: MutableList<Stein>,
    var rausgekommen: Boolean = false,
    private val validateInitialCount: Boolean = true,
) {
	init {
		if (validateInitialCount) {
			require(brett.size == 14) { "Es muss 14 Steine vergeben werden" }
		}
	}

	val nameReadOnly: String
		get() = name
	val brettReadOnly: MutableList<Stein>
		get() = brett.toMutableList()

//	fun punktzahl(): Int {
//		var punkte = 0
//		for (stein in brett) {
//			punkte += stein.zahl().value
//		}
//		return punkte
//	}

	fun ziehen(
        stein: Stein,
	) {
		brett.add(stein)
	}

	fun entfernen(
        stein: Stein,
	) {
		val funktioniert = brett.remove(stein)
		require(funktioniert) { "Der Stein ist nicht vorhanden." }
	}
}