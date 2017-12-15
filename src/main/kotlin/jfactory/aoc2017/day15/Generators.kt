package jfactory.aoc2017.day15

fun Long.lowestBits(n:Int) = this.and( Math.pow(2.toDouble(), n.toDouble()).toLong() - 1)
fun Int.lowestBits(n:Int) = this.and( Math.pow(2.toDouble(), n.toDouble()).toInt() - 1)

fun main(args: Array<String>) {

    val genA = generateSequence(634L){ ((it*16807) % 2147483647)}
    val genB = generateSequence(301L){ ((it*48271) % 2147483647)}

    println( genA.zip(genB)
            .take(40_000_000)
            .count{it.first.and(65535) == it.second.and(65535)})

    println( genA.filter { it % 4L == 0L }.zip(genB.filter { it % 8L == 0L })
            .take(5_000_000)
            .count{it.first.and(65535) == it.second.and(65535)})

}