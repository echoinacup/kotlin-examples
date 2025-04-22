package com.echoinacup

import com.echoinacup.dto.ExampleData
import com.echoinacup.dto.ExampleToCompare
import com.echoinacup.dto.NestedData
import com.echoinacup.dto.strValLengthComparator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple

class AssertJFeatures {

    /*
    *  Working with List collections.
    * */
    @Test
    fun `testing collections`() {
        val strValOne = "one"
        val longValOne = 1L
        val nested = NestedData("nestedVal")
        val exampleDataOne = ExampleData(strVal = strValOne, longVal = longValOne, nested)
        val strValTwo = "two"
        val longValTwo = 2L
        val exampleDataTwo = ExampleData(strVal = strValTwo, longVal = longValTwo)
        val listToTest = listOf(exampleDataOne, exampleDataTwo)

        // extraction of not nested fields
        assertThat(listToTest)
            .extracting("strVal", "longVal")
            .containsExactly(Tuple.tuple(strValOne, longValOne), Tuple.tuple(strValTwo, longValTwo))
        // extracting with nested one
        assertThat(listToTest)
            .extracting("strVal", "longVal", "nested.nestedStrVal")
            .containsExactly(Tuple.tuple(strValOne, longValOne, "nestedVal"), Tuple.tuple(strValTwo, longValTwo, null))

        // extracting with lambda
        assertThat(listToTest)
            .element(0) // fetching the first element of the list.
            .extracting { data ->
                assertThat(data.nested).isEqualTo(nested)
            }
    }

    @Test
    fun `testing comparison for lists`() {
        val two = ExampleToCompare("aa")
        val one = ExampleToCompare("a")
        val three = ExampleToCompare("ccc")
        val four = ExampleToCompare("bbbb")
        val sortedList = listOf(two, one, three, four).sortedWith(strValLengthComparator)

        assertThat(sortedList)
            .isSortedAccordingTo(Comparator.comparing { it.strVal.length })
    }

    // testing map asserts
}