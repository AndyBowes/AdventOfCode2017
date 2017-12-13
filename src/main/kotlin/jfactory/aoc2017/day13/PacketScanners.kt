package jfactory.aoc2017.day13

typealias Firewall = Map<Int, Int>

fun parseInput(lines: List<String>):Map<Int,Int>{
    return lines.map { it.split(": ") }
            .associate { it[0].toInt() to it[1].toInt() }
}

fun Firewall.penalty() = this.entries.filter { (k, v) -> isBlocked(k,v) }.sumBy { (k, v) -> k * v }

fun Firewall.cleanPass() = generateSequence(0){ it+1 }.first{ delay -> this.entries.none { (k,v) -> isBlocked(k, v, delay) }}

private fun isBlocked(id: Int, length: Int, delay: Int =0) = (delay + id) % (2 * (length - 1)) == 0


fun main(args: Array<String>) {
    val firewall = parseInput(inputTest.split("\n"))
    println(firewall.penalty())
    println(firewall.cleanPass())
}

val inputTest = """0: 3
1: 2
4: 4
6: 4""".trimIndent()

val input = """0: 3
1: 2
2: 6
4: 4
6: 4
8: 10
10: 6
12: 8
14: 5
16: 6
18: 8
20: 8
22: 12
24: 6
26: 9
28: 8
30: 8
32: 10
34: 12
36: 12
38: 8
40: 12
42: 12
44: 14
46: 12
48: 12
50: 12
52: 12
54: 14
56: 14
58: 14
60: 12
62: 14
64: 14
66: 17
68: 14
72: 18
74: 14
76: 20
78: 14
82: 18
86: 14
90: 18
92: 14""".trimIndent()