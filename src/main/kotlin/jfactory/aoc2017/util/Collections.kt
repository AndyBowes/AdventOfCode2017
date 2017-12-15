package jfactory.aoc2017.util

/**
 * Reverse a section within a Mutable List
 * This function treats the List as a Circular List so that the flipped region can overlap the end of the list.
 */
fun <E> MutableList<E>.flip(start: Int, count: Int)  : MutableList<E>{
    val idx = (start until start + count).map { it % this.size }
    this.slice(idx).reversed().zip(idx).forEach {
        this[it.second] = it.first
    }
    return this
}

/**
 * Accumulate Function
 *
 * Basically a Combination of Fold and Map which uses the result of previous element to act as the entry into the next.
 */
inline fun <T, R> Iterable<T>.accumulate(initial: R, operation: (previous: R, T) -> R): List<R> {
    var accumulator = arrayListOf(initial)
    for (element in this) accumulator.add(operation(accumulator.last(), element))
    return accumulator
}