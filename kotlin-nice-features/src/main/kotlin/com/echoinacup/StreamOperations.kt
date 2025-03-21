package com.echoinacup

// fold function examples

fun List<Int>.sumListValues(): Int {
    return this.fold(0) { acc, num -> acc + num }
}

fun List<String>.concatenationStrings(): String {
    return this.fold("") { acc, str -> acc + str }
}

fun List<Int>.findMaxVal(): Int {
    return this.fold(Int.MIN_VALUE) { acc, num -> maxOf(acc, num) }
}

fun String.countCharOccurrences(): MutableMap<Char, Int> {
    val counts: MutableMap<Char, Int> = mutableMapOf()
    return this.fold(counts) { acc, char ->
        acc[char] = acc.getOrPut(char) { 0 } + 1
        acc
    }
}

fun multiplyNumbers(numbers: List<Int>): Int {
    return numbers.fold(1) { acc, num ->
        println("acc: $acc, num: $num → result: ${acc * num}")
        acc * num  // Custom reduction function (multiplication)
    }
}

// Zip function examples

fun pairTwoLists(listOne: List<Int>, listTwo: List<Int>): List<Pair<Int, Int>> {
    return listOne.zip(listTwo)
}

fun sumTwoListsElementsBetweenEachOther(listOne: List<Int>, listTwo: List<Int>): List<Int> {
    return listOne.zip(listTwo) { valFromFirst, valFromSecond ->
        valFromFirst + valFromSecond
    }
}

fun mergeTwoListsIntoMap(stringList: List<String>, intList: List<Int>): Map<String, Int> {
    return stringList.zip(intList) { strVal, intVal -> strVal to intVal }.toMap()
}

// scan function
fun cumulativeSum(valueToSum: List<Int>): List<Int> {
    return valueToSum.scan(0) { acc, currentVal -> acc + currentVal }
}

// associateWith
fun List<String>.associateStringToLength(): Map<String, Int> {
    return this.associateWith { it.length }
}