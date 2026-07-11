package hwr.oop.rummikub_2026.ports.out

import hwr.oop.rummikub_2026.core.Spiel

interface SaveGamePort {

    fun save(spiel: Spiel): Unit

}