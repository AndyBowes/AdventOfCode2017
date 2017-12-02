package jfactory.aoc2017.day1

fun naughtyOrNice(input: String): Int {
    val nums = input.map { it.charToInt() }
    return (listOf(nums.last()).plus(nums)).zipWithNext()
            .filter { it.first == it.second }
            .map{it.first}.sum()
}

fun Char.charToInt() = if (this.isDigit()) this.toString().toInt() else throw IllegalArgumentException("Not a digit :$this")

fun naughtyOrNice2(input: String): Int {
    val nums = input.map { it.charToInt() }
    val nums2 = (input.substring(input.length/2) + input).map { it.charToInt() }
    return nums.zip(nums2)
            .filter { it.first == it.second }
            .map{it.first}.sum()
}


