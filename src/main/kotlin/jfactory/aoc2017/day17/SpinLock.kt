package jfactory.aoc2017.day17

fun Pair<MutableList<Int>,Int>.addEntry(element: Int, steps: Int) : Pair<MutableList<Int>, Int>{
    val nextPos = ((this.second+steps)%(element) + 1)
    this.first.add(nextPos, element)
    return this.first to nextPos
}

fun part1(steps: Int) : Int {
    val (result,_) = (1..2017).fold(Pair(mutableListOf(0),0)){ prev, n -> prev.addEntry(n, steps)}
    return result[result.indexOf(2017)+1]
}

fun part2(steps: Int) : Int {
    val result = (1..50000000).fold(Pair(0, 0)){ prev, n ->
        var idx = (prev.first + steps)%n
        idx + 1 to if (idx==0) n else prev.second
    }
    return result.second
}

fun main(args: Array<String>) {
    val steps = 369
    println(part1(steps))
    println(part2(steps))
}