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


/**
 * Memoization which is backed by a Mutable Map
 * Provide a function which will be used to populate the missing elements as they are requested.
 */
class MemoizedMap<K,V>(val items: MutableMap<K,V>, private val memoFunction : (K) -> V) : MutableMap<K,V> by items {
    override fun get(key: K): V {
        val value = items.get(key)
        return if (value == null) {
            val answer = memoFunction.invoke(key)
            items.put(key, answer)
            answer
        } else {
            value
        }
    }
}
