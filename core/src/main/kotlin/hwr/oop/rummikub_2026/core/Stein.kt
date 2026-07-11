package hwr.oop.rummikub_2026.core
import kotlinx.serialization.Serializable

@Serializable
data class Stein(private val farbe: Farbe, private var zahl: Zahl) {
	fun farbe(): Farbe = farbe
	fun zahl(): Zahl = zahl

	fun jokerWertAnpassen(gewuenschteZahl: Zahl) {
		if (farbe == Farbe.Joker) {
			zahl = gewuenschteZahl
		}
	}
}