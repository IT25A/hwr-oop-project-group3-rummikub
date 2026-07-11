package hwr.oop.rummikub_2026.core

object SpielManager {
    //var spiel: Spiel? = null
    val zuege: MutableList<Zug> = mutableListOf()

    fun erstelleZufaelligesSpiel(
        players: List<SpielerId>,
        gameId: SpielId = SpielId.random(),
    ): Spiel {
        richtigeSpielerZahl(players)

        val beutel = Beutel()

        val spieler = verteileKarten(players, beutel)

        return Spiel(
            id = gameId,
            aktivSpieler = spieler[0],
            beutel = beutel.beutelReadOnly,
            tisch = Tisch(mutableListOf()),
            spieler = spieler
        )
    }
    private fun richtigeSpielerZahl(players: List<SpielerId>) {
        require(players.size in 2..4) {}
    }

    private fun verteileKarten(
        players: List<SpielerId>,
        beutel: Beutel,
    ): List<Spieler> {
        val spielerMitKarten = mutableListOf<Spieler>()
       var bretter = beutel.austeilen(players.size).toMutableList()
       for (player in players) {
           spielerMitKarten.add(Spieler(id = player, name = "Spieler ${player.value}", brett = bretter[0].toMutableList()))
           bretter.removeAt(0)
       }
        return spielerMitKarten
    }
}