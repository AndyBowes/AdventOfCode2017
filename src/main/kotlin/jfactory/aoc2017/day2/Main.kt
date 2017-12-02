package jfactory.aoc2017.day2

import java.io.File

class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = File(Main::class.java.getResource("input1.txt").file).readLines()
            println(input)
        }
    }

}