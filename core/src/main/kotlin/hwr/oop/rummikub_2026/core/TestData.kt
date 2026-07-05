package hwr.oop.rummikub_2026.core

class TestData {
    val testspiel = Spiel(
        Spieler(
            "testspieler",
            "12345",
            mutableListOf(),
            true,
            true
        ),
        listOf(),
        Tisch(mutableListOf())
    )
}