package jfactory.aoc2017.day3

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*

class SpiralTest {

    @Test
    fun `Given a setp of one then distance should be zero`() {
        assertThat(distance(1), equalTo(0))
    }

    @Test
    fun `After 12 steps then distance should be 3`() {
        assertThat(distance(12), equalTo(3))
    }

    @Test
    fun `After 23 steps then distance should be 2`() {
        assertThat(distance(23), equalTo(2))
    }

    @Test
    fun `After 1024 steps then distance should be 31`() {
        assertThat(distance(1024), equalTo(31))
    }
}