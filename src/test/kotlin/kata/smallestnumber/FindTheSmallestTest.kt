package kata.smallestnumber

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FindTheSmallestTest {

    private val sut = FindTheSmallestNumber()

    @ParameterizedTest
    @MethodSource("testDataProvider")
    fun actuallyDoFind(number: Long, result: ResultDto) {
        sut.actuallyDoFind(number) shouldBe result
    }

    companion object {
        @JvmStatic
        fun testDataProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.of(1L, ResultDto(1, 0, 0)),
                Arguments.of(10, ResultDto(1, 0, 1)),
                Arguments.of(20, ResultDto(2, 0, 1)),
                Arguments.of(100, ResultDto(1, 0, 2)),
                Arguments.of(261235, ResultDto(126235, 2, 0)),
                Arguments.of(261235, ResultDto(126235, 2, 0)),
                Arguments.of(209917, ResultDto(29917, 0, 1)),
                Arguments.of(285365, ResultDto(238565, 3, 1)),
                Arguments.of(269045, ResultDto(26945, 3, 0)),
                Arguments.of(296837, ResultDto(239687, 4, 1)),
                Arguments.of(187863002809L, ResultDto(18786300289, 10, 0)),
                Arguments.of(199819884756L, ResultDto(119989884756, 4, 0)),
                Arguments.of(94883608842L, ResultDto(9488368842, 6, 0)),
                Arguments.of(256687587015L, ResultDto(25668758715, 9, 0)),
                Arguments.of(935855753L, ResultDto(358557539, 0, 8)),
                Arguments.of(222222222222L, ResultDto(222222222222, 0, 0)),
                Arguments.of(195819884756L, ResultDto(119589884756, 4, 0)),
                Arguments.of(322222222222L, ResultDto(222222222223, 0, 11)),
            )
    }
}


//testing(261235, "[126235, 2, 0]")
//testing(209917, "[29917, 0, 1]")
//testing(285365, "[238565, 3, 1]")
//testing(269045, "[26945, 3, 0]")
//testing(296837, "[239687, 4, 1]")
//
//testing(187863002809L, "[18786300289, 10, 0]")
//testing(199819884756L, "[119989884756, 4, 0]")
//testing(94883608842L, "[9488368842, 6, 0]")
//testing(256687587015L, "[25668758715, 9, 0]")
//testing(935855753L, "[358557539, 0, 8]")
//testing(222222222222L, "[222222222222, 0, 0]")
//testing(195819884756L, "[119589884756, 4, 0]")
//testing(322222222222L, "[222222222223, 0, 11]")