package com.echoinacup

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StreamOperationsKtTest {
    @Test
    fun `test fold for sum values`() {
        val values = listOf(1, 2, 3, 4, 5)
        assertThat(values.sumListValues()).isEqualTo(15)
    }

    @Test
    fun `test fold for concat values`() {
        val values = listOf("One", "Two", "Three")
        assertThat(values.concatenationStrings()).isEqualTo("OneTwoThree")
    }

    @Test
    fun `test fold for max value`() {
        val values = listOf(1, 2, 3, 4, 5)
        assertThat(values.findMaxVal()).isEqualTo(5)
    }

    @Test
    fun `count chars in a string`() {
        val result = "aabbcc".countCharOccurrences()
        assertThat(result).containsAllEntriesOf(mapOf('a' to 2, 'b' to 2, 'c' to 2))
    }

    @Test
    fun `fold for multiplication of values`() {
        assertThat(multiplyNumbers(listOf(2, 3, 4))).isEqualTo(24)
    }

    @Test
    fun `pair two lists`() {
        val oddList = listOf(1, 3, 5)
        val eventList = listOf(2, 4, 6)
        assertThat(pairTwoLists(oddList, eventList)).contains(1 to 2, 3 to 4, 5 to 6)
    }

    @Test
    fun `sum values from two lists to each other`() {
        val oddList = listOf(1, 3, 5)
        val eventList = listOf(2, 4, 6)
        assertThat(sumTwoListsElementsBetweenEachOther(oddList, eventList)).containsExactly(3, 7, 11)
    }

    @Test
    fun `merge string list and int list into map`() {
        val digitNames = listOf("one", "two", "three")
        val intValues = listOf(1, 2, 3)

        assertThat(mergeTwoListsIntoMap(digitNames, intValues)).containsAllEntriesOf(
            mapOf(
                "one" to 1,
                "two" to 2,
                "three" to 3
            )
        )
    }
}