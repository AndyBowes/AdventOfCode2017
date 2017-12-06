package jfactory.aoc2017.day6

fun List<Int>.reallocate(): List<Int>{
    val (maxIndex, memorySize) = this.withIndex().maxBy { it.value }!!
    val reallocation = this.toMutableList()
    reallocation[maxIndex] = 0
    (1..memorySize).map { (maxIndex + it)%this.size }.forEach {
        reallocation[it] += 1
    }
    return reallocation
}

tailrec fun memoryAllocation(allocation: List<Int>, history: List<List<Int>> = listOf()) : Pair<Int, Int>{
    if (allocation in history){
        return Pair(history.size, history.size - history.indexOf(allocation))
    }
    return memoryAllocation(allocation.reallocate(), history.plusElement(allocation))
}

fun main(args: Array<String>) {
    println(memoryAllocation(listOf(4,10,4,1,8,4,9,14,5,1,14,15,0,15,3,5)))
}