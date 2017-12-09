package jfactory.aoc2017.day9

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*

class StreamProcessingTest {

    @Test
    fun `Given {} should return a sum of 1`() {
        assertThat(process("{}").sum()!!, equalTo(1))
    }

    @Test
    fun `Given {{{}}} should return a sum of 6`() {
        assertThat(process("{{{}}}").sum()!!, equalTo(6))
    }

    @Test
    fun `Given {{},{}} should return a sum of 5`() {
        assertThat(process("{{},{}}").sum()!!, equalTo(5))
    }

    @Test
    fun `Given {{{},{},{{}}}} should return a sum of 16`() {
        assertThat(process("{{{},{},{{}}}}").sum()!!, equalTo(16))
    }

    @Test
    fun `Given {a,a,a,a} should return a sum of 1`() {
        assertThat(process("{<a>,<a>,<a>,<a>}").sum()!!, equalTo(1))
    }

    @Test
    fun `Given {{ab},{ab},{ab},{ab}} should return a sum of 9`() {
        assertThat(process("{{<ab>},{<ab>},{<ab>},{<ab>}}").sum()!!, equalTo(9))
    }

    @Test
    fun `Given string with escape characters should return a sum of 9`() {
        assertThat(process("{{<!!>},{<!!>},{<!!>},{<!!>}}").sum()!!, equalTo(9))
    }

    @Test
    fun `Given string with escape character infront of braces should return a sum of 3`() {
        assertThat(process("{{<a!>},{<a!>},{<a!>},{<ab>}}").sum()!!, equalTo(3))
    }


//    {{{}}}, score of 1 + 2 + 3 = 6.
//    {{},{}}, score of 1 + 2 + 2 = 5.
//    {{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.
//    {<a>,<a>,<a>,<a>}, score of 1.
//    {{<ab>},{<ab>},{<ab>},{<ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
//    {{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.
//    {{<a!>},{<a!>},{<a!>},{<ab>}}, score of 1 + 2 = 3
}