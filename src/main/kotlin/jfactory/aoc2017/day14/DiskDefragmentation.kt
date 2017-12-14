package jfactory.aoc2017.day14

import jfactory.aoc2017.day10.*
import java.math.BigInteger
import java.util.*
import kotlin.coroutines.experimental.buildSequence

fun String.toBinary(bits: Int) = BigInteger(this,16).toString(2).padStart(bits,'0')

fun Int.knotHashAsBinary(key:String = "hxtvlmkl") = knotHash("$key-$this").toBinary(128)

fun String.countBits() = this.count { it == '1' }

fun connectGrid(size: Int =  128) : Int {

    val grid = object {
        val gridRange = (0 until size)
        val UNVISITED = '*'
        val internal: MutableMap<Pair<Int,Int>, Char> = mutableMapOf()

        // Populate the Grid by Setting the positions of all the 1's to UNVISITED
        init {
            gridRange.forEach {
               y -> run {
                    y.knotHashAsBinary().forEachIndexed { x, c -> set(x to y, if (c == '1') UNVISITED else '-') }
                }
            }
        }

        fun printGrid() {
            gridRange.forEach { y ->
                run { gridRange.forEach { x -> print(get(x to y)) } }
                println()
            }
        }

        fun get(pos: Pair<Int,Int>) = internal[pos]
        fun set(pos: Pair<Int,Int>, value: Char) = internal.put(pos, value)

        fun firstUnconnected() = internal.entries.firstOrNull{ (_, v) -> v == UNVISITED }?.let { it.key }

        fun unvisitedNeighbours(pos: Pair<Int,Int>) = neighbours(pos).filter{ it.first in gridRange && it.second in gridRange && get(it) == UNVISITED}

        private fun neighbours(pos: Pair<Int, Int>) = listOf(Pair(pos.first + 1, pos.second), Pair(pos.first - 1, pos.second), Pair(pos.first, pos.second + 1), Pair(pos.first, pos.second - 1))

        fun connectNeighbours(start: Pair<Int,Int>, groupId: Int){
            val queue = ArrayDeque<Pair<Int,Int>>()
            queue.add(start)
            while (queue.isNotEmpty()){
                val pos = queue.pop()!!
                if (get(pos) == UNVISITED) {
                    set(pos, ('a'.toInt() + groupId % 25).toChar())
                    queue.addAll(unvisitedNeighbours(pos))
                }
            }
        }
    }

    return buildSequence {
        var groupId = 0
        while (true) {
            val nextNode: Pair<Int, Int>? = grid.firstUnconnected()
            if (nextNode == null) {
                break
            } else {
                groupId++
                grid.connectNeighbours(nextNode, groupId)
                yield(groupId)
            }
        }
    }.last()

}

fun main(args: Array<String>) {
    println((0..127).map{ it.knotHashAsBinary()}.sumBy(String::countBits))
    println(connectGrid(128))
}
