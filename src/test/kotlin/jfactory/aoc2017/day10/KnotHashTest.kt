package jfactory.aoc2017.day10

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*

class KnotHashTest {

    @Test
    fun `Given the test input 3, 4, 1, 5 check that Part 1 generates the expected result`() {
        assertThat(part1(listOf(3, 4, 1, 5),size=5), equalTo(12))
    }

    @Test
    fun `Given input of AoC 2017 then denseHash should produce 33efeb34ea91902bb2f59c9920caa6cd`() {
        assertThat(denseHash("AoC 2017", suffix), equalTo("33efeb34ea91902bb2f59c9920caa6cd"))
    }
    @Test
    fun `Given empty input then denseHash should produce a2582a3a0e66e6e86e3812dcb672a272`() {
        assertThat(denseHash("", suffix), equalTo("a2582a3a0e66e6e86e3812dcb672a272"))
    }
    @Test
    fun `Given input of 1,2,3 then denseHash should produce 3efbe78a8d82f29979031a4aa0b16a9d`() {
        assertThat(denseHash("1,2,3", suffix), equalTo("3efbe78a8d82f29979031a4aa0b16a9d"))
    }
    @Test
    fun `Given input of 1,2,4 then denseHash should produce 63960835bcdc130f0b66d7ff4f6a5a8e`() {
        assertThat(denseHash("1,2,4", suffix), equalTo("63960835bcdc130f0b66d7ff4f6a5a8e"))
    }
}