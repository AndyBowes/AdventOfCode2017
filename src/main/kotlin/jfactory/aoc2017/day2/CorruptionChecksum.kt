package jfactory.aoc2017.day2

import kotlin.math.max
import kotlin.math.min

fun Iterable<Int>.minMaxDiff() : Int {
    val minMax = this.fold( Pair(Int.MAX_VALUE, Int.MIN_VALUE),
            {acc, i -> Pair(min(acc.first, i), max(acc.second,i))})
    return minMax.second - minMax.first
}

fun Iterable<Int>.divisor() = this.flatMap { x-> this.map{ y -> Pair(x,y)}
                                            .filter { it.first != it.second && it.second % it.first == 0 } }
                                    .first()
                                    .run { second/first }


fun Iterable<Iterable<Int>>.corruptionChecksum() = this.map { it.minMaxDiff() }.sum()

fun Iterable<Iterable<Int>>.divisorChecksum() = this.map { it.divisor() }.sum()