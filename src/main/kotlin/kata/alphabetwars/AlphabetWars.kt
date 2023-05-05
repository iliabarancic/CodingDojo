package kata.alphabetwars

import kata.alphabetwars.Result.LEFT_WINS
import kata.alphabetwars.Result.RIGHT_WINS
import kata.alphabetwars.Result.TIE

class AlphabetWars {

    private val scores = mapOf(
        'w' to 4,
        'p' to 3,
        'b' to 2,
        's' to 1,
        'm' to -4,
        'q' to -3,
        'd' to -2,
        'z' to -1
    )

    fun fight(input: String): Result {

        val score = decideWinner(explodeBombs(input))

        return when {
            score > 0 -> LEFT_WINS
            score < 0 -> RIGHT_WINS
            else -> TIE
        }
    }

    private fun decideWinner(input: String) = input.map { scores[it] ?: 0 }.sum()

    private fun explodeBombs(input: String): String {
        val regex = """([a-z*]\*[a-z*])""".toRegex()
        return input.replace(regex, "")
    }

}

enum class Result {
    LEFT_WINS,
    RIGHT_WINS,
    TIE
}
