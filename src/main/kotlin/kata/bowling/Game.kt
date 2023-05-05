package kata.bowling

class Game {

    private val frames: MutableList<Frame> = mutableListOf()

    fun roll(pins: Int) {
        if (pins < 0 || pins > 10) throw InvalidNumberOfPinsException("invalid roll")

        // 1. first Frame or frame completed
        if (frames.isEmpty() || frames.last().isCompleted) {
            frames += Frame(pins)
        } else {
            // 2. open frame
            frames.last().secondRoll = pins
        }
    }

    fun score(): Int {
        var sum = 0

        frames.forEachIndexed { i, frame ->
            sum += frame.total

            if (frame.isSpare) {
                sum += frames[i + 1].firstRoll
            }

            if (frame.isStrike) {
                if (frames[i + 1].isStrike) {
//                    sum += TODO()
                } else {
                    sum += frames[i + 1].total
                }
            }
        }

        return sum
    }

    class Frame(val firstRoll: Int) {
        var secondRoll: Int? = null

        val total get() = firstRoll + (secondRoll ?: 0)

        var foo
            get() = secondRoll
            private set(value) {
                secondRoll = value
            }

        val isStrike get() = firstRoll == 10
        val isSpare get() = isCompleted && !isStrike && (firstRoll + secondRoll!!) == 10
        val isCompleted get() = isStrike || secondRoll != null
    }
}

class InvalidNumberOfPinsException(s: String?) : IllegalArgumentException(s)