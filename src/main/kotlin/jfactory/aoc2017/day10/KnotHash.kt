package jfactory.aoc2017.day10

fun <E> MutableList<E>.flip(start: Int, count: Int) {
    val idx = (start until start + count).map { it % this.size }
    this.slice(idx).reversed().zip(idx).forEach {
        this[it.second] = it.first
    }
}

fun part1(l: List<Int>, size: Int = 256): Int {
    val state = (0 until size).toMutableList()
    var start = 0
    val (resultState, _) = performRound(l, state, start)
    return resultState[0] * resultState[1]
}

fun denseHash(input: String, suffix: List<Int>): String {
    val lengths = (input.toCharArray().map { it.toInt() } + suffix)
    val state = (0 until 256).toMutableList()
    val (sparseHash, _) = (0 until 64).fold(Pair(state, 0), { previous, i ->
                performRound(lengths, previous.first, previous.second, i * lengths.size)
        })
    return sparseHash.chunked(16, { it -> it.fold(0, { x, n -> x xor n}).toString(16)}).joinToString("")
}

private fun performRound(input: List<Int>, state: MutableList<Int>, startPos: Int, offset: Int = 0): Pair<MutableList<Int>, Int> {
    var start1 = startPos
    input.forEachIndexed { skip, i ->
        state.flip(start1, i)
        start1 = (start1 + i + skip + offset) % state.size
    }
    return Pair(state, start1)
}

fun main(args: Array<String>) {
    val nums = input.split(",").map { it.toInt() }
    println(part1(listOf()))
    println(203.toString(16))
    println(denseHash(input, listOf(17, 31, 73, 47, 23)))
}

val input = "227,169,3,166,246,201,0,47,1,255,2,254,96,3,97,144"