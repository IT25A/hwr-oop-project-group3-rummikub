package hwr.oop.rummikub_2026.core

class Folge(listOf: List<Stein>) {
 init{
     require(listOf.size >= 3){"Must have at least 3 items"}
 }
}
