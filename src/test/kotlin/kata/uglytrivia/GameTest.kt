package kata.uglytrivia

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension

@ExtendWith(OutputCaptureExtension::class)
class GameTest {
    @Test
    fun add_player_should_increase_player_count(output: CapturedOutput) {
        val sut = Game()

        sut.add("Georg")

//        sut.players shouldContain "Georg"

        sut.howManyPlayers() shouldBe  1

        output.all shouldBe  """Georg was added
They are player number 1
"""
    }

    @Test
    fun roll(){
        val sut = Game()
        sut.add("p1")
        sut.add("p2")

        sut.places[0] shouldBe 0

        sut.roll(1)

        sut.places[0] shouldBe 1
    }

    @Test
    fun game_should_print_correct_output(output: CapturedOutput) {
        val sut = Game()
        sut.add("Player1")
        sut.add("Player2")


        sut.roll(1)
        sut.wasCorrectlyAnswered()

        sut.roll(2)
        sut.wrongAnswer()

        output.all.shouldBe(
"""Player1 was added
They are player number 1
Player2 was added
They are player number 2
Player1 is the current player
They have rolled a 1
Player1's new location is 1
The category is Science
Science Question 0

""".trimIndent())
    }

}