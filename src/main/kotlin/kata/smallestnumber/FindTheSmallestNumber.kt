package kata.smallestnumber

class FindTheSmallestNumber {

    fun actuallyDoFind(number: Long): ResultDto {
        val possibleResults = mutableListOf<ResultDto>()
        number.toString().forEachIndexed { i: Int, iChar: Char ->

            //     v
            //  15512
            // ^
            //
            //  1 5 5 2
            // ^ ^ ^ ^ ^

            val numberStringWithoutI = number.toString().removeRange(i, i + 1)

            (0..numberStringWithoutI.length).forEach { j ->
                StringBuilder(numberStringWithoutI)
                    .apply {
                        insert(j, iChar)
                    }
                    .toString()
                    .toLong()
                    .let { ResultDto(it, i, j) }
                    .also(possibleResults::add)
            }
        }

        return possibleResults.minByOrNull { it.number }!!
    }

    fun actuallyDoFind2(number: Long) =
        number
            .toString()
            .flatMapIndexed { i: Int, iChar: Char ->
                val numberStringWithoutI = number.toString().removeRange(i, i + 1)
                (0..numberStringWithoutI.length)
                    .map { j ->
                        StringBuilder(numberStringWithoutI)
                            .apply { insert(j, iChar) }
                            .toString()
                            .toLong()
                            .let { ResultDto(it, i, j) }
                    }
            }
            .sortedWith(compareBy({ it.number }, { it.fromIndex }, { it.toIndex }))
            .first()
}

data class ResultDto(
    val number: Long,
    val fromIndex: Int,
    val toIndex: Int,
)