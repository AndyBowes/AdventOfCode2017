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
    fun `Given 0, 2, 7, 0 When memory reallocated should return 5`(){
        assertThat(memoryAllocation(listOf(0,2,7,0)), equalTo(5))
    }

}