package hwr.oop.project.group3.rummikub.core

data class Stein(
	private val farbe: Farbe,
	private val zahl: Zahl,
) {
	fun farbe(): Farbe = farbe
	fun zahl(): Zahl = zahl
}
