package hwr.oop.rummikub_2026.core

data class Zug(
    val aktiverSpieler: SpielerId,
    val kombis: List<Kombinationen>,
    val angelegt: List<Pair<Kombinationen, Stein>>,
    val auseinandergezogen: List<Kombinationen>,
    val gezogen: Stein
)