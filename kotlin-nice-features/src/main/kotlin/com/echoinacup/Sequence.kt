package com.echoinacup

import java.time.LocalDate

/*
*  Adding one day up to the point where we meet predicate requirements.
* */
fun LocalDate.nextMatchingPredicate(predicate: (LocalDate) -> Boolean): LocalDate {
    return generateSequence(this) { it.plusDays(1) }.first(predicate)
}