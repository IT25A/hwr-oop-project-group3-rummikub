package hwr.oop.rummikub_2026.core

data class Spieler(
	private val name: String,
	val id: String,
	private val board: MutableList<Stein>,
	var rausgekommen: Boolean = false,
	private val validateInitialCount: Boolean = true

) {
	init {
		if (validateInitialCount) {
			require(board.size == 14) { "Es muss 14 Steine vergeben werden" }
		}
	}
	
	val nameReadOnly: String
		get() = name
	val boardReadOnly: MutableList<Stein>
		get() = board.toMutableList()
	
	fun ziehen(stein: Stein) {
		board.add(stein)
	}
	
	fun entfernen(stein: Stein) {
		val funktioniert = board.remove(stein)
		require(funktioniert) { "Der Stein ist nicht vorhanden." }
	}
}
