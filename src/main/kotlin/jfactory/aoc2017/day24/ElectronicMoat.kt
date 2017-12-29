package jfactory.aoc2017.day24

data class Element(val a:Int,val b:Int){
    val value = a + b
    fun canJoin(x: Int) = a==x || b==x
    fun otherEnd(x:Int) = if (a==x) b else a
}

typealias Bridge = List<Element>
fun Bridge.value() = this.sumBy { it.value }

fun buildBridge(remaining: List<Element>, x: Int = 0, bridge: Bridge = emptyList(), comparator: Comparator<Bridge> = compareBy(Bridge::value)) : Bridge {
    return remaining.filter { it.canJoin(x) }
            .map { buildBridge(remaining - it, it.otherEnd(x), bridge + it, comparator) }
                .maxWith(comparator) ?: bridge
}


fun main(args: Array<String>) {
    val elements = input.split("\n")
            .map { it.split("/")
                    .map(String::toInt)}.map { e-> Element(e[0], e[1])}
    println(buildBridge(elements).value())
    println(buildBridge(elements, comparator = compareBy(Bridge::size) then compareBy(Bridge::value)).value())
}


val input = """31/13
34/4
49/49
23/37
47/45
32/4
12/35
37/30
41/48
0/47
32/30
12/5
37/31
7/41
10/28
35/4
28/35
20/29
32/20
31/43
48/14
10/11
27/6
9/24
8/28
45/48
8/1
16/19
45/45
0/4
29/33
2/5
33/9
11/7
32/10
44/1
40/32
2/45
16/16
1/18
38/36
34/24
39/44
32/37
26/46
25/33
9/10
0/29
38/8
33/33
49/19
18/20
49/39
18/39
26/13
19/32"""