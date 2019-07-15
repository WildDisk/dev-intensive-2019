package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    //    TODOs("not implemented") //To change body of created functions use File | Settings | File Templates.
    val tmp = this.time
    this.time = tmp
    var time = Date().add(2, TimeUnits.SECOND).time - date.time
    print(time)
    return this.toString()
}

private fun plurals(value: Long): Long {
    var n = when(value) {
        0L -> 0L
        else -> 5L
    }
    abs(n) % 100
    return 0L
}




enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}