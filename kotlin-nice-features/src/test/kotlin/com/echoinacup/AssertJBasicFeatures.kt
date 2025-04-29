package com.echoinacup

import com.echoinacup.dto.ExampleData
import com.echoinacup.dto.ExampleToCompare
import com.echoinacup.dto.NestedData
import com.echoinacup.dto.strValLengthComparator
import com.echoinacup.service.CustomNumericException
import com.echoinacup.service.NumericService
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.data.MapEntry
import org.assertj.core.groups.Tuple
import java.time.LocalDate

class AssertJBasicFeatures {

    /*
    *  Asserting objects
    * */
    @Test
    fun `checking object fields and nested objects`() {
        val strValOne = "one"
        val longValOne = 1L
        val nested = NestedData("nestedVal")
        val exampleDataWithNestedStruct = ExampleData(strVal = strValOne, longVal = longValOne, nested)
        val exampleDataWithNullableNested = ExampleData(strVal = strValOne, longVal = longValOne)

        // fields for nullability
        assertThat(exampleDataWithNullableNested)
            .hasNoNullFieldsOrPropertiesExcept("nested")

        assertThat(exampleDataWithNestedStruct)
            .isNotNull()
            .extracting("strVal", "longVal", "nested.nestedStrVal")
            .containsExactly(strValOne, longValOne, "nestedVal")

        assertThat(exampleDataWithNestedStruct)
            .extracting(
                { it.strVal },
                { it.longVal },
                { it.nested!!.nestedStrVal }
            ).containsExactly(strValOne, longValOne, "nestedVal")

        // or we can go this way
        assertThat(exampleDataWithNestedStruct).satisfies({ data ->
            assertThat(data.strVal).isEqualTo(strValOne)
            assertThat(data.longVal).isPositive().isEqualTo(longValOne)
            assertThat(data.nested!!.nestedStrVal).isEqualTo("nestedVal")
        })
    }

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
            .doesNotHaveDuplicates()
            .isSubsetOf(exampleDataOne, exampleDataTwo)
            .extracting("strVal", "longVal")
            .containsExactly(Tuple.tuple(strValOne, longValOne), Tuple.tuple(strValTwo, longValTwo))
        // extracting with nested one
        assertThat(listToTest)
            .extracting("strVal", "longVal", "nested.nestedStrVal")
            .containsExactly(Tuple.tuple(strValOne, longValOne, "nestedVal"), Tuple.tuple(strValTwo, longValTwo, null))
        // extracting with lambda
        assertThat(listToTest)
            .element(0) // fetching the first element of the list.
            .extracting { data -> data.nested }.isEqualTo(nested)
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

        // numbers in the list and ordering
        val numbers = listOf(1, 2, 3)
        assertThat(numbers).isSorted
        assertThat(numbers.reversed()).isSortedAccordingTo(Comparator.reverseOrder())

        // or just to test sorted dates
        val sortedDates = listOf(
            LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 6, 1),
            LocalDate.of(2024, 1, 1)
        )

        assertThat(sortedDates).isSorted

        // satisfy as usually in the end for LocalDates
        val yesterday = LocalDate.now().minusDays(1)
        val dates = listOf(yesterday, yesterday.minusDays(2), yesterday.minusWeeks(1))

        assertThat(dates).allSatisfy {
            assertThat(it).isBefore(LocalDate.now())
        }
    }

    // testing map asserts
    @Test
    fun `testing maps`() {
        val testedMap = mapOf("one" to 1, "two" to 2)

        assertThat(testedMap)
            .containsKey("one")
            .containsValue(2)
            .containsEntry("one", 1)
            .doesNotContainKey("five")
            .doesNotContain(MapEntry.entry("five", 5))
            .containsExactly(
                MapEntry.entry("one", 1),
                MapEntry.entry("two", 2)
            )
            .extractingByKey("two")
            .isEqualTo(2)

        assertThat(testedMap).allSatisfy { key, value ->
            assertThat(key).isNotEmpty()
            assertThat(value).isPositive()
        }
    }

    @Test
    fun `test for exception handling`() {
        val serviceToTest = NumericService()
        assertThatThrownBy {
            serviceToTest.parseStringToLong("none")
        }.isInstanceOf(CustomNumericException::class.java)
            .hasMessage("Please validate logs, the value is incorrect: none")
            .extracting {
                it.cause
            }.isInstanceOf(NumberFormatException::class.java)

        // or satisfies
        assertThatExceptionOfType(CustomNumericException::class.java)
            .isThrownBy {
                serviceToTest.parseStringToLong("none")
            }.satisfies({
                assertThat(it.message).isEqualTo("Please validate logs, the value is incorrect: none")
                assertThat(it.cause).isInstanceOf(NumberFormatException::class.java)
            })

        // or we can go this way
        assertThatExceptionOfType(CustomNumericException::class.java)
            .isThrownBy {
                serviceToTest.parseStringToLong("none")
            }.extracting(
                { it.message },
                { it.cause!!.javaClass }
            ).containsExactly(
                "Please validate logs, the value is incorrect: none",
                NumberFormatException::class.java
            )
    }
}