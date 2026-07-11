package hwr.oop.rummikub_2026.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestDataTest {

    @Test
    fun `testspiel is initialized correctly`() {
        val testData = TestData()

        val spiel = testData.testspiel

        assertNotNull(spiel)
        assertEquals("testspieler", spiel.aktivSpieler.nameReadOnly)
        assertEquals(SpielerId("testspielerId"), spiel.aktivSpieler.id)
        assertTrue(spiel.aktivSpieler.rausgekommen)
        assertTrue(spiel.beutel.isEmpty())
        assertTrue(spiel.tisch.tischReadOnly.isEmpty())
        assertTrue(spiel.spieler.isEmpty())
    }
}