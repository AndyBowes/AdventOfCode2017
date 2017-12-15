package jfactory.aoc2017.day15

//fun Long.lowestBits(n: Int) = this.and(Math.pow(2.toDouble(), n.toDouble()).toLong() - 1)
//fun Int.lowestBits(n: Int) = this.and(Math.pow(2.toDouble(), n.toDouble()).toInt() - 1)
//

fun Long.lowBits() = this and 0xffff
private fun numGenerator(seed: Long, multiplier: Int) = generateSequence(seed) { (it * multiplier) % 2147483647 }

fun main(args: Array<String>) {

    val genA = numGenerator(634L, 16807)
    val genB = numGenerator(301L, 48271)

    println(genA.zip(genB)
            .take(40_000_000)
            .count { (a, b) -> a and 0xffff == b and 0xffff })

    println(genA.filter { it % 4L == 0L }.zip(genB.filter { it % 8L == 0L })
            .take(5_000_000)
            .count { (a, b) -> a.lowBits() == b.lowBits() })

}