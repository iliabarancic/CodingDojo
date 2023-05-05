package kata.bowling

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Test {

    @Test
    fun missedAll() {
        val game = Game()

        repeat(20) { game.roll(0) }

        game.score() shouldBe 0
    }

    @Test
    fun everyTime1Pin() {
        val game = Game()

        repeat(20) { game.roll(1) }

        game.score() shouldBe 20
    }

    @Test
    fun rollWith11Pins() {
        val game = Game()

        shouldThrow<InvalidNumberOfPinsException> { game.roll(11) }

    }

    @Test
    fun rollWithNegativePins() {
        val game = Game()

        shouldThrow<InvalidNumberOfPinsException> { game.roll(-1) }

    }

    @Test
    fun rolledASpare__nextThrowDoubled() {
        val game = Game()

        game.roll(2)
        game.roll(8)
        game.roll(5)
        game.roll(1)

        repeat(16) { game.roll(0) }

        game.score() shouldBe 21
    }

    @Test
    fun rolledAStrike__nextTwoThrowsDoubled() {
        val game = Game()

        game.roll(10)
        game.roll(5)
        game.roll(1)

        repeat(17) { game.roll(0) }

        game.score() shouldBe 22
    }

    @Test
    fun rolledAStrike__twoStrikesInARow() {
        val game = Game()

        game.roll(10)
        game.roll(10)
        game.roll(1)

        repeat(17) { game.roll(0) }

        game.score() shouldBe 33
    }
}