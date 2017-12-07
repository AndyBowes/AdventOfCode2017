package jfactory.aoc2017.day7

import java.io.File

class Program(val name: String, val weight: Int, val children: List<String> = emptyList())

fun List<Program>.findBaseProgram(): Program {
    return this.asSequence().filter { program -> this.none { it.children.contains(program.name) } }.first()
}

class BalancePrograms(programList: List<Program>) {

    private val programs = programList.associateBy { it.name }
    private val totalWeights = mutableMapOf<String, Int>()

    private fun calculateTotalWeight(programName: String): Int {
        return programs.getValue(programName).weight + programs.getValue(programName).children.map { getTotalWeight(it) }.sum()
    }

    private fun getTotalWeight(programName: String) = totalWeights.getOrPut(programName, { calculateTotalWeight(programName) })

    fun findUnbalancedChild(programName: String, delta: Int): Pair<String,Int> {
        val program = programs.getValue(programName)

//        println("Program  $programName:    Delta: $delta")
//        program.children.forEach { println("    $it : ${getTotalWeight(it)}") }
//        println("   ------------      ")

        return when (program.children.size) {
            0 -> Pair(programName, program.weight + delta)
            1 -> findUnbalancedChild(program.children[0],0)
            else -> pickOddOneOut(program)?.run { findUnbalancedChild(this.first, this.second) }
                    ?: Pair(programName, program.weight + delta)
        }
    }

    private fun pickOddOneOut(program: Program): Pair<String,Int>? {
        val childrenByWeight = program.children.groupBy { getTotalWeight(it) }
        val oddOneOut = childrenByWeight.filter { it.value.size == 1 }
        return when (oddOneOut.size){
            0 -> null
            1 -> {
                val oddWeight = oddOneOut.keys.first()
                val delta = childrenByWeight.keys.filter { it != oddWeight }.first() - oddWeight
                Pair(oddOneOut.values.first().first(),delta)
            }
            else -> null
        }
    }
}

fun readProgramFile(fileName: String): List<Program> {
    val input = File("/Users/andybowes/dev/abowes/adventOfCode2017/src/main/kotlin/jfactory/aoc2017/day7", fileName)
    return input.readLines().map { it.toProgram() }
}

val PROGRAM_REGEX = Regex("""^(\w*) \((\d*)\)( -> (.*))?$""")

private fun String.toProgram(): Program {
    val matchResult = PROGRAM_REGEX.matchEntire(this)
    return when (matchResult) {
        null -> throw IllegalArgumentException(this)
        else -> {
            val children : List<String> = when (matchResult.groupValues[4]){
                null -> emptyList()
                else -> matchResult.groupValues[4].split(", ").filter { it.isNotEmpty() }
            }
            Program(matchResult.groupValues[1], matchResult.groupValues[2].toInt(), children )
        }
    }
}

fun main(args: Array<String>) {
    val programs = readProgramFile("pavel.txt")
    val rootProgram = programs.findBaseProgram().name
    println(rootProgram)
    val balancer = BalancePrograms(programs)
    println(balancer.findUnbalancedChild(rootProgram, 0))
}



