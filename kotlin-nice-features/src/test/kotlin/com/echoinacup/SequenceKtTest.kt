package com.echoinacup

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat

class SequenceKtTest {

    @ParameterizedTest
    @MethodSource("dateForWeekend")
    fun `test local date sequence`(testInput: LocalDate, expected: LocalDate) {
        assertThat(testInput.nextMatchingPredicate { currentDate -> !currentDate.isWeekend() }).isEqualTo(expected)
    }


    companion object {
        @JvmStatic
        fun dateForWeekend(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 10)),
                Arguments.of(LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 17)),
                Arguments.of(LocalDate.of(2025, 3, 16), LocalDate.of(2025, 3, 17))
            )
        }
    }
}