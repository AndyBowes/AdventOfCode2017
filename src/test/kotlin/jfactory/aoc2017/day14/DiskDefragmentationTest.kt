package jfactory.aoc2017.day14

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*

class DiskDefragmentationTest {

    @Test
    fun fromHexToBinary() {
        assertThat('0'.fromHexToBinary(), equalTo("0"))
        assertThat('1'.fromHexToBinary(), equalTo("1"))
        assertThat('2'.fromHexToBinary(), equalTo("10"))
        assertThat('8'.fromHexToBinary(), equalTo("1000"))
        assertThat('9'.fromHexToBinary(), equalTo("1001"))
        assertThat('a'.fromHexToBinary(), equalTo("1010"))
        assertThat('b'.fromHexToBinary(), equalTo("1011"))
        assertThat('c'.fromHexToBinary(), equalTo("1100"))
        assertThat('d'.fromHexToBinary(), equalTo("1101"))
        assertThat('e'.fromHexToBinary(), equalTo("1110"))
        assertThat('f'.fromHexToBinary(), equalTo("1111"))
    }

}