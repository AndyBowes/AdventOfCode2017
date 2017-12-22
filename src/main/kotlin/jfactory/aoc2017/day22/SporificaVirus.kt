package jfactory.aoc2017.day22

class Grid(initalState: String, val debug:Boolean = false){
    val map = mutableMapOf<Pair<Int,Int>,Char>()
    var direction = Pair(0,-1)

    var currentPos = Pair(0,0)
    var infected = 0
    init {
        val lines = initalState.split("\n")
        currentPos = Pair((lines[0].length/2), lines.size/2)
        lines.forEachIndexed { y, s -> s.forEachIndexed { x, c -> setChar(x,y,c) } }
        if (debug) printGrid()
    }

    fun charAt(pos: Pair<Int,Int>) = charAt(pos.first,pos.second)
    fun charAt(x:Int,y:Int) = map.getOrPut(x to y, {'.'})
    fun setChar(pos:Pair<Int,Int>, c:Char) = setChar(pos.first,pos.second, c)
    fun setChar(x:Int, y:Int, c: Char) = map.put(x to y, c)

    fun turnLeft(){
        direction = Pair(direction.second, direction.first * -1)
    }

    fun turnRight(){
        direction = Pair(direction.second*-1, direction.first)
    }

    fun reverse(){
        direction = Pair(-direction.first, -direction.second )
    }

    fun move(){
        when (charAt(currentPos)){
            '#' -> cleanNode(currentPos)
            else -> infectNode(currentPos)
        }
        currentPos = Pair(currentPos.first + direction.first, currentPos.second + direction.second)

        if (debug){ printGrid() }
    }

    private fun printGrid() {
        println("=".repeat(40))
        println("Pos= $currentPos")
        println("Dir= $direction")
        (-5..10).forEach { y -> println((-5..10).map{ x-> charAt(x,y)}.joinToString(""))}
    }

    private fun cleanNode(pos: Pair<Int, Int>) {
        turnRight()
        setChar(pos, '.')
    }

    private fun infectNode(pos: Pair<Int,Int>){
        infected += 1
        turnLeft()
        setChar(pos, '#')
    }

    fun doMoves(n: Int) : Int {
        repeat(n){ move()}
        return infected
    }

}

fun main(args: Array<String>) {
    val grid = Grid(input)
    println(grid.doMoves(10000))
}


val testInput = """..#
#..
..."""


val input = """#..###...#..#.#.#...#..##
....#.##..#..###...###...
##.#.....###...#.##...###
##...#.####..#####..####.
##.#...#.##...##.....##.#
###.#.#...###..###.###...
#.#..#.#.###..#.##.#..###
.#..###.##..##.#....#.#..
#.#.......###.##...#.##..
#.#.######.##.#..#...#...
######.#.##...#.#...###.#
.#....#.###.##.######....
#.#####.#####.#.#..##.###
..##.#.#...###......###.#
.##.##..##.#.#.#######.##
#..###.###....#.....##..#
..##..####..##.#...####..
.##.####.##.##..##..#....
###...#.#..##...#.#..##..
......##.....#.#..#.#.###
#.#.##.##.#####....#.#..#
.....#.###.##...#...#..#.
#...#......##.##.#####.##
#.##.##.......#.##....#.#
####.##.#.#........###.##"""