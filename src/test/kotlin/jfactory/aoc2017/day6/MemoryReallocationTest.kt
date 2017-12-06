package jfactory.aoc2017.day6

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.*
import org.junit.Test

class MemoryReallocationTest{

    @Test
    fun `Given 0, 2, 7, 0 When memory reallocated should return 2 4 1 2`(){
        assertThat(listOf(0,2,7,0).reallocate(), equalTo(listOf(2,4,1,2)))
    }

    @Test
    fun `Given 0, 2, 7, 0 When memory reallocated should return 5,3`(){
        assertThat(memoryAllocation(listOf(0,2,7,0)), equalTo(Pair(5,4)))
    }

    @Test
    fun `Given supplied input When memoryAllocation is run Then accepted answer is returned`(){
        assertThat(memoryAllocation(listOf(4,10,4,1,8,4,9,14,5,1,14,15,0,15,3,5)), equalTo(Pair(12841,8038)))
    }
}