package jfactory.aoc2017.day18

import kotlinx.coroutines.experimental.TimeoutCancellationException
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.experimental.runBlocking
import kotlinx.coroutines.experimental.withTimeout
import kotlin.system.measureTimeMillis

fun Map<String, Long>.regValue(r: String): Long = r.toLongOrNull() ?: this.getOrDefault(r, 0)

fun part1(instructions: List<String>): Long {
    var ptr = 0
    val registers = mutableMapOf<String, Long>()

    while (ptr >= 0 && ptr < instructions.size) {
        val instruction = instructions[ptr]

        val elements = instruction.split(" ")
        val op = elements[0]
        val a = elements[1]
        val b = if (elements.size > 2) registers.regValue(elements[2]) else 0

        when (op) {
            "set" -> registers.set(a, b)
            "add" -> registers.set(a, registers.regValue(a) + b)
            "mul" -> registers.set(a, registers.regValue(a) * b)
            "mod" -> registers.set(a, registers.regValue(a) % b)
            "snd" -> registers.set("sound", registers.regValue(a))
            "rcv" -> if (registers.regValue(a) != 0L) {
                return registers.regValue("sound")
            }
            "jgz" -> if (registers.regValue(a) > 0L) {
                ptr += b.toInt() - 1
            }
        }

        ptr++
    }
    return -1
}

suspend fun part2(instructions: List<String>, progId: Int, incoming: Channel<Long>, outgoing: Channel<Long>): Int {
    var ptr = 0
    val registers = mutableMapOf<String, Long>()
    registers.set("p", progId.toLong())
    var sent = 0

    loop@ while (ptr >= 0 && ptr < instructions.size) {
        val instruction = instructions[ptr]

        val elements = instruction.split(" ")
        val op = elements[0]
        val a = elements[1]
        val b = if (elements.size > 2) registers.regValue(elements[2]) else 0

        when (op) {
            "set" -> registers.set(a, b)
            "add" -> registers.set(a, registers.regValue(a) + b)
            "mul" -> registers.set(a, registers.regValue(a) * b)
            "mod" -> registers.set(a, registers.regValue(a) % b)
            "snd" -> outgoing.send(registers.regValue(a)).also { sent++ }
            "rcv" -> try {
                registers.set(a, withTimeout(20) { incoming.receive() }) // Assume timeout will only occur on Deadlock.
            } catch (e: TimeoutCancellationException) {
                break@loop
            }
            "jgz" -> if (registers.regValue(a) > 0L) {
                ptr += b.toInt() - 1  // Shift Pointer Back 1 more so that normal increment is accounted for.
            }
        }

        ptr++
    }
    return sent
}


fun main(args: Array<String>) {
    val instructions = input.split("\n")
    println("Part 1: (ms) "+  measureTimeMillis {
        println(part1(instructions))
    } )

    println("Part 2: (ms) " + measureTimeMillis {
        runBlocking {
            val channel0to1 = Channel<Long>(UNLIMITED)
            val channel1to0 = Channel<Long>(UNLIMITED)
            val prog0 = async { part2(instructions, 0, channel0to1, channel1to0) }
            val prog1 = async { part2(instructions, 1, channel1to0, channel0to1) }
            println("Answer is : ${prog1.await()}")
        }
    })
}


val testInput = """set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""


val input = """set i 31
set a 1
mul p 17
jgz p p
mul a 2
add i -1
jgz i -2
add a -1
set i 127
set p 826
mul p 8505
mod p a
mul p 129749
add p 12345
mod p a
set b p
mod b 10000
snd b
add i -1
jgz i -9
jgz a 3
rcv b
jgz b -1
set f 0
set i 126
rcv a
rcv b
set p a
mul p -1
add p b
jgz p 4
snd a
set a b
jgz 1 3
snd b
set f 1
add i -1
jgz i -11
snd a
jgz f -16
jgz a -19"""