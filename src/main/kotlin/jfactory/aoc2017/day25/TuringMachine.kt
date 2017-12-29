package jfactory.aoc2017.day25

class Transition(val write: Int, dir: String, val toState: String){
    val direction : Int

    init {
        direction = if (dir=="right") 1 else -1
    }
}

fun main(args: Array<String>) {

    val lines = input.split("\n").iterator()
    val initialState = Regex("^Begin in state (\\w).").matchEntire(lines.next())?.groupValues!![1]
    val checksumCount = Regex("Perform a diagnostic checksum after (\\d+) steps.").matchEntire(lines.next())?.groupValues!![1].toInt()

    val transitions = mutableMapOf<Pair<String,Int>,Transition>()
    while (lines.hasNext()){
        lines.next()

        val fromState = Regex("^In state (\\w):").matchEntire(lines.next())?.groupValues!![1]
        repeat(2){
            val currentVal = Regex(".*If the current value is (\\d):").matchEntire(lines.next())?.groupValues!![1].toInt()
            val writeVal = Regex(".*(\\d).*").matchEntire(lines.next())?.groupValues!![1].toInt()
            val direction = Regex(".*Move one slot to the (\\w+).").matchEntire(lines.next())?.groupValues!![1]
            val toState = Regex(".*Continue with state (\\w+).").matchEntire(lines.next())?.groupValues!![1]
            transitions.put(Pair(fromState,currentVal), Transition(writeVal,direction,toState))
        }
    }

    val start = Triple(initialState, 0, mutableMapOf<Int,Int>())
    val finalState = (0 until checksumCount).fold(start){ prev, _ ->
        val transition  = transitions.getValue(Pair(prev.first, prev.third.getOrDefault(prev.second,0)))
        prev.third[prev.second] = transition.write
        Triple(transition.toState, prev.second + transition.direction, prev.third )
    }

    println(finalState.third.values.count { it ==1 })

}







val input="""Begin in state A.
Perform a diagnostic checksum after 12317297 steps.

In state A:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state B.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the left.
    - Continue with state D.

In state B:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state C.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the right.
    - Continue with state F.

In state C:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state C.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state A.

In state D:
  If the current value is 0:
    - Write the value 0.
    - Move one slot to the left.
    - Continue with state E.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state A.

In state E:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state A.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the right.
    - Continue with state B.

In state F:
  If the current value is 0:
    - Write the value 0.
    - Move one slot to the right.
    - Continue with state C.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the right.
    - Continue with state E."""