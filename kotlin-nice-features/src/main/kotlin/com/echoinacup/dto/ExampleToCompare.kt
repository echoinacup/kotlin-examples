package com.echoinacup.dto

data class ExampleToCompare(val strVal: String)

val strValLengthComparator = Comparator<ExampleToCompare> { firstToCompare, secondToCompare ->
    firstToCompare.strVal.length.compareTo(secondToCompare.strVal.length)
}