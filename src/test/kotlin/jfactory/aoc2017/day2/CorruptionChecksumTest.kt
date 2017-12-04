package jfactory.aoc2017.day2

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.util.stream.Stream




class CorruptionChecksumTest {

    fun readResource(path: String): Stream<String>? {
        val inputStream = File(CorruptionChecksumTest::class.java.getResource("input1.txt").file)
        return inputStream.bufferedReader().lines()
    }

    @Test
    fun `Given 5 1 9 & 5 Then minMaxDiff should be 8`(){
        assertThat(listOf(5,1,9,5).minMaxDiff(), equalTo(8))
    }

    @Test
    fun `Given 7 5 & 3 Then minMaxDiff should be 4`(){
        assertThat(listOf(7,5,3).minMaxDiff(), equalTo(4))
    }

    @Test
    fun `Given 2 4 6 & 8 Then minMaxDiff should be 6`(){
        assertThat(listOf(2,4,6,8).minMaxDiff(), equalTo(6))
    }

    @Test
    fun `Given supplied numbers then corruption checksum should be`(){
        assertThat(
                listOf(listOf(5,1,9,5),listOf(7,5,3), listOf(2,4,6,8)).corruptionChecksum(), equalTo(18))
    }


    @Test
    fun `Given 5 9 2 8 then the divisor should be 4`(){
        assertThat(listOf(5,9,2,8).divisor(), equalTo(4))
    }

    @Test
    fun `Given 9 4 7 3 then the divisor should be 3`(){
        assertThat(listOf(9,4,7,3).divisor(), equalTo(3))
    }

    @Test
    fun `Given 3 8 6 5 then the divisor should be 2`(){
        assertThat(listOf(3,8,6,5).divisor(), equalTo(2))
    }



    @Test
    fun `Read Input from file`() {
        val resource = readResource("input1.txt")
        val input = resource?.map { it.split("\t").map { it.toInt() } }
        //println(input?.divisorChecksum())
    }
}