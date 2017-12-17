package jfactory.aoc2017.util

fun String.rotateRight(n: Int) = this.substring(this.length - n) + this.substring(0, this.length - n)
fun String.swapByPosition(a: Int, b: Int): String {
    val chars = this.toCharArray()
    chars.set(b, this.get(a))
    chars.set(a, this.get(b))
    return String(chars)
}

fun String.swapByValue(a: Char, b: Char) = this.swapByPosition(this.indexOf(a), this.indexOf(b))
