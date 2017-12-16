package jfactory.aoc2017.day16

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*

class PermutationPromenadeTest {

    @Test
    fun spin() {
        assertThat("abcde".spin(1), equalTo("eabcd"))
        assertThat("abcde".spin(3), equalTo("cdeab"))
    }
}