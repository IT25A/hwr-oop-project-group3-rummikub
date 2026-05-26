package hwr.oop.rummikub_2026.core

data class Spieler(private val name: String, private val id: String, private val board: MutableList<Stein>)
{
    init {
        require(board.size == 14) { "Es muss 14 Steine vergeben werden" }
    }
    val nameReadOnly: String
        get() = name
    val boardReadOnly: List<Stein>
        get() = board.toList()

    fun ziehen(stein: Stein) {
        board.add(stein)
    }

    fun entfernen(stein: Stein) {

        val funktioniert = board.remove(stein)
        require(funktioniert){ "Der Stein ist nicht vorhanden."}
    }
}
