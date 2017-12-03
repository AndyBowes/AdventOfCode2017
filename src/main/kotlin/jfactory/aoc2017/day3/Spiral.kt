package jfactory.aoc2017.day3

import kotlin.coroutines.experimental.buildSequence
import kotlin.math.abs

fun spiral():Sequence<Pair<Int,Int>> = buildSequence {

    var x = 0
    var y = 0
    var dx = 0
    var dy = 1

    while (true){
        yield(Pair(x,y))
        if ((abs(x)==abs(y) && (x < 0 || y<0)) || (x>=0 && 1==(y-x))){
            val tmp = dx
            dx = -dy
            dy = tmp
        }
        x += dx
        y += dy
    }
}

fun distance(n:Int) = spiral().take(n).last().run { abs(first) + abs(second) }

fun fillSpiral(n:Int) : Int {
    val DIRECTIONS = listOf(Pair(-1,-1),Pair(-1,0),Pair(-1,1), Pair(0,-1),Pair(0,1), Pair(1,-1),Pair(1,0),Pair(1,1))
    fun fillGrid(grid: MutableMap<Pair<Int,Int>,Int>, pos: Pair<Int, Int>): Int {
        grid[pos] = when (pos){
            Pair(0,0) -> 1
            else -> DIRECTIONS.map { Pair(pos.first + it.first, pos.second + it.second)}
                    .map{grid.getOrDefault( it ,0) }.sum()
        }
        return grid[pos]!!
    }

    val grid = mutableMapOf<Pair<Int,Int>,Int>()
    return spiral().dropWhile { fillGrid(grid, it) <= n}.first().run { grid[this]!! }
}


fun main(args: Array<String>) {
    println(distance(312051))
    println(fillSpiral(312051))
}