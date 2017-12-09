package jfactory.aoc2017.day9

import java.io.File
import kotlin.coroutines.experimental.buildSequence

fun process(input:String, part2: Boolean = false):Sequence<Int> = buildSequence {

    var skip = false
    var garbage = false
    var level = 0
    var discarded = 0

    input.forEach {
        when {
            skip -> skip = false
            it == '!' && garbage -> skip = true
            it == '>' && garbage -> garbage = false
            it == '<' && !garbage -> garbage = true
            it == '{' && !garbage -> { level++ ; yield (level)}
            it == '}' && !garbage -> if (level > 0) level--
            garbage -> discarded ++
        }
    }
    if (part2) yield (discarded)  // Last element in the list is the number of discarded characters
}

fun readInputFile(fileName: String): String {
    return File("/Users/andybowes/dev/abowes/adventOfCode2017/src/main/kotlin/jfactory/aoc2017/day9", fileName).readText()
}

fun main(args: Array<String>) {
    println(process("{{<!!>},{<!!>},{<!!>},{<!!>}}").sum())
    println(process(readInputFile("input.txt"),part2 = true).last())
}

