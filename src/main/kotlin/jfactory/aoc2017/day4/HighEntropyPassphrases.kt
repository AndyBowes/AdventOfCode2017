package jfactory.aoc2017.day4

import java.io.File

fun List<String>.isValidPassphase( ) : Boolean {
    return (this.size == this.toSet().size && !this.containsAnagram())
}

fun List<String>.containsAnagram(): Boolean {
    return this.size != this.distinctBy{ it.toList().sorted() }.size
}

fun main(args: Array<String>) {
    println(System.getProperty("user.dir"))
    println(File("src/main/kotlin/jfactory/aoc2017/day4/input4.txt").readLines()
            .map { it.split(" ") }
            .filter{it.isValidPassphase()}
            .count())
}
