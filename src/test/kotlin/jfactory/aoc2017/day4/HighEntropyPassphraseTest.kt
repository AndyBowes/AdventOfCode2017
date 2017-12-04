package jfactory.aoc2017.day4

import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Test

class HighEntropyPassphraseTest {

    @Test
    fun `Given a Passphrase of "aa bb cc dd ee" then it should be valid`() {
        Assert.assertThat(listOf("aa", "bb", "cc", "dd", "ee").isValidPassphase(), IsEqual.equalTo(true))
    }

    @Test
    fun `Given a Passphrase of "aa bb cc dd aa" then it should be invalid`() {
        Assert.assertThat(listOf("aa", "bb", "cc", "dd", "aa").isValidPassphase(), IsEqual.equalTo(false))
    }

    @Test
    fun `Given a Passphrase of "aa bb cc dd aaa" then it should be valid`() {
        Assert.assertThat(listOf("aa", "bb", "cc", "dd", "aaa").isValidPassphase(), IsEqual.equalTo(true))
    }

    @Test
    fun `Given a Passphrase of "abcde fghij" then it should be valid`() {
        Assert.assertThat(listOf("abcde","fghij").isValidPassphase(), IsEqual.equalTo(true))
    }

    @Test
    fun `Given a Passphrase of "abcde xyz ecdab" then it should be invalid`() {
        Assert.assertThat(listOf("abcde","xyz","ecdab").isValidPassphase(), IsEqual.equalTo(false))
    }

    @Test
    fun `Given a Passphrase of "a ab abc abd abf abj" then it should be valid`() {
        Assert.assertThat(listOf("a","ab","abc","abd","abf","abj").isValidPassphase(), IsEqual.equalTo(true))
    }

    @Test
    fun `Given a Passphrase of "iiii oiii ooii oooi oooo" then it should be valid`() {
        Assert.assertThat(listOf("iiii","oiii","ooii","oooi","oooo").isValidPassphase(), IsEqual.equalTo(true))
    }

    @Test
    fun `Given a Passphrase of "oiii ioii iioi iiio" then it should be invalid`() {
        Assert.assertThat(listOf("oiii","ioii","iioi","iiio").isValidPassphase(), IsEqual.equalTo(false))
    }

}
