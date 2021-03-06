package jfactory.aoc2017.day10

import jfactory.aoc2017.util.flip

fun part1(input: List<Int>, size: Int = 256): Int {
    val state = (0 until size).toMutableList()
    val (resultState, _) = performRound(input, state, 0)
    return resultState[0] * resultState[1]
}

fun knotHash(input: String, suffix: List<Int> = listOf(17, 31, 73, 47, 23)): String {
    val lengths = (input.toCharArray().map { it.toInt() } + suffix)
    val state = (0 until 256).toMutableList()
    val (sparseHash, _) = (0 until 64).fold(Pair(state, 0), { previous, i ->
                performRound(lengths, previous.first, previous.second, i * lengths.size)
        })
    return sparseHash.chunked(16){ it.reduce(Int::xor) }.joinToString(""){"%02x".format(it)}
}

private fun performRound(input: List<Int>, state: MutableList<Int>, startPos: Int, offset: Int = 0): Pair<MutableList<Int>, Int> {
    return input.foldIndexed(state to startPos){
        i, acc, length -> acc.first.flip(acc.second, length) to (acc.second + i + length + offset)%acc.first.size
    }
}

fun main(args: Array<String>) {
    val nums = input.split(",").map(String::toInt)
    println(part1(nums))
    println(knotHash(input))
}

val input = "227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144"