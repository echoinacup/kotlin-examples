package com.echoinacup

import java.time.DayOfWeek
import java.time.LocalDate

fun LocalDate.isWeekend(): Boolean {
    val dayOfWeek = this.dayOfWeek
    return (dayOfWeek == DayOfWeek.SATURDAY).or(dayOfWeek == DayOfWeek.SUNDAY)
}