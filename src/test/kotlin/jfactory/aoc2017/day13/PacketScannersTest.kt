package jfactory.aoc2017.day13

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*

class PacketScannersTest {

    private val testData = mapOf(0 to 3, 1 to 2, 4 to 4, 6 to 4)

    @Test
    fun testPenalty() {
        assertThat(testData.penalty(), equalTo(24))
    }

    @Test
    fun cleanPass() {
        assertThat(testData.cleanPass(), equalTo(10))
    }
}