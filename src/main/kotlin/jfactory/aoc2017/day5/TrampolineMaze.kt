package jfactory.aoc2017.day5

import java.io.File

fun main(args: Array<String>) {
    val jumps = File("src/main/kotlin/jfactory/aoc2017/day5/input5.txt").readLines().map { it.toInt() }
    println( countJumps(jumps))
}

fun countJumps(jumps: List<Int>): Int {
    val j = jumps.toMutableList()
    var ptr = 0
    var count = 0
    while (ptr < jumps.size){
        val prevPtr = ptr
        ptr += j[ptr]
        j[prevPtr] += if (j[prevPtr] >= 3) -1 else 1
        count += 1
    }
    return count
}
