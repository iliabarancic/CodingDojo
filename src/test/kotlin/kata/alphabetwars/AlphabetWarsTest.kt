package kata.alphabetwars

import io.kotest.matchers.shouldBe
import kata.alphabetwars.Result.LEFT_WINS
import kata.alphabetwars.Result.RIGHT_WINS
import kata.alphabetwars.Result.TIE
import org.junit.jupiter.api.Test

class AlphabetWarsTest {

    private val sut = AlphabetWars()

    @Test
    fun `fight empty string is tie`() {
        sut.fight("") shouldBe TIE
    }

    @Test
    fun `fight simple left side wins`() {
        sut.fight("w") shouldBe LEFT_WINS
    }

    @Test
    fun `fight simple right side wins`() {
        sut.fight("m") shouldBe RIGHT_WINS
    }

    @Test
    fun `fight simple tie`() {
        sut.fight("wm") shouldBe TIE
    }

    @Test
    fun `fight simple bomb tie`() {
        sut.fight("w*q") shouldBe TIE
    }

    @Test
    fun `fight z only`() {
        sut.fight("z") shouldBe RIGHT_WINS
    }

    @Test
    fun `fight bombs only`() {
        sut.fight("****") shouldBe TIE
    }

    @Test
    fun `fight wyld1`() {
        sut.fight("z*dq*mw*pb*s") shouldBe TIE
    }

    @Test
    fun `fight wyld2`() {
        sut.fight("zdqmwpbs") shouldBe TIE
    }

    @Test
    fun `fight wyld3`() {
        sut.fight("zz*zzs") shouldBe RIGHT_WINS
    }

    @Test
    fun `fight wyld4`() {
        sut.fight("sz**z**zs") shouldBe LEFT_WINS
    }

    @Test
    fun `fight wyld5`() {
        sut.fight("z*z*z*zs") shouldBe LEFT_WINS
    }

    @Test
    fun `fight wyld6`() {
        sut.fight("*wwwwww*z*") shouldBe LEFT_WINS
    }

}