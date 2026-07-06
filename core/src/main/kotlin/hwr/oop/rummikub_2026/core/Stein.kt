package hwr.oop.rummikub_2026.core
import kotlinx.serialization.Serializable
@Serializable
data class Stein(private val farbe: Farbe, private val zahl: Zahl) {
	fun farbe(): Farbe = farbe
	fun zahl(): Zahl = zahl
}