package jfactory.aoc2017.duet

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.*
import org.junit.Test

class FractalArtTest{

    @Test
    fun `Given pattern When I rotate Clockwise Then I get expected Result`(){
        assertThat(listOf(".#","..").rotateClockwise(), equalTo(listOf("..", ".#")))
        assertThat(listOf("..",".#").rotateClockwise(), equalTo(listOf("..", "#.")))
        assertThat(listOf("##","..").rotateClockwise(), equalTo(listOf(".#", ".#")))
        assertThat(listOf(".#.","..#","###").rotateClockwise(), equalTo(listOf("#..","#.#","##.")))
    }

    @Test
    fun `Given pattern When I flip it Then I get expected Result`(){
        assertThat(listOf(".#.","..#","###").flipHorizontal(), equalTo(listOf(".#.","#..","###")))
    }

    @Test
    fun `Given a pattern When I get permutations then I get expected result`(){
        assertThat(listOf(".##",".#.", "...").permutations().size, equalTo(8))
    }


    @Test
    fun `Given a 2x2 layout check When Split into Blocks Then should generate single block`(){
        assertThat(listOf(".#","..").splitIntoBlocks(), equalTo(listOf(listOf(listOf(".#", "..")))))
    }

    @Test
    fun `Given a 3x3 layout check When Split into Blocks Then should generate single block`(){
        assertThat(listOf(".#.","..#","###").splitIntoBlocks(), equalTo(listOf(listOf(listOf(".#.","..#","###")))))
    }

    @Test
    fun `Given a 4x4 layout check When Split into Blocks Then should generate 4 Blocks`(){
        assertThat(listOf(".#.#","..##","####", "....").splitIntoBlocks(), equalTo(listOf(listOf(listOf(".#",".."),listOf(".#","##")),
                listOf(listOf("##",".."),listOf("##","..")))))
    }

    @Test
    fun `Given a set of Blocks When they are joined then it generates expected Layout`(){
        val expectedLayout = listOf("#..#","....","....","#..#")
        val blocks = listOf((listOf(listOf("#.", ".."), listOf(".#", ".."))), listOf(listOf("..", "#."), listOf("..", ".#")))
        assertThat(blocks.join(), equalTo(expectedLayout))
    }

}