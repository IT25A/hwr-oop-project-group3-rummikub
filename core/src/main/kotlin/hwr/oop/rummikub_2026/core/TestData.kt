package hwr.oop.rummikub_2026.core

class TestData {
	val testspiel = Spiel(
		Spieler(
			"testspieler",
			SpielerId("testspielerId"),
			mutableListOf(),
			true,
			false
		),
		listOf(),
		Tisch(mutableListOf()),
		listOf()
	)
}