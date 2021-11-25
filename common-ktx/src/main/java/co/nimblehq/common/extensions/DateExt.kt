package co.nimblehq.common.extensions

import co.nimblehq.common.extensions.date.DateRange
import co.nimblehq.common.extensions.date.TimeInterval
import java.text.SimpleDateFormat
import java.util.*

val tomorrow: Date
    get() = shiftDate(value = 1)

val yesterday: Date
    get() = shiftDate(value = -1)

/**
 * Shift the date with the specific value
 *
 * @param value - days you want to shift from today
 *
 * @return the shifted date from today
 */
private fun shiftDate(value: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.add(Calendar.DATE, value)
    return calendar.time
}

/**
 * Move forward with the type specific time interval like day, week, month, year, ex : Date() + 1.week
 *
 * @param duration - time in interval, ex 1.day, 1.week
 *
 * @return Date plus interval time
 */
operator fun Date.plus(duration: TimeInterval): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(duration.unit, duration.value)
    return calendar.time
}

/**
 * Move backward with the type specific time interval like day, week, month, year
 *
 * @param duration - time in interval, ex 1.day, 1.week
 *
 * @return Date minus interval time
 */
operator fun Date.minus(duration: TimeInterval): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(duration.unit, -duration.value)
    return calendar.time
}

/**
 * Get the range between two date
 *
 * @param endDate - Date to set range with the current
 *
 * @return DateRange from current to target
 */
operator fun Date.rangeTo(endDate: Date) = DateRange(this, endDate)

/**
 * Set the date with specific time
 *
 * @param year - positive int
 * @param month - positive int
 * @param day - positive int
 * @param hour - positive int
 * @param minute - positive int
 * @param second - positive int
 * @param millisecond - positive int
 *
 * @return Date with inputted values
 */
fun Date.with(
    year: Int = -1,
    month: Int = -1,
    day: Int = -1,
    hour: Int = -1,
    minute: Int = -1,
    second: Int = -1,
    millisecond: Int = -1
): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    if (year > -1) calendar.set(Calendar.YEAR, year)
    if (month > 0) calendar.set(Calendar.MONTH, month - 1)
    if (day > 0) calendar.set(Calendar.DATE, day)
    if (hour > -1) calendar.set(Calendar.HOUR_OF_DAY, hour)
    if (minute > -1) calendar.set(Calendar.MINUTE, minute)
    if (second > -1) calendar.set(Calendar.SECOND, second)
    if (millisecond > -1) calendar.set(Calendar.MILLISECOND, millisecond)
    return calendar.time
}

/**
 * Set the current date to the weekday of month
 *
 * @param weekday - week of month
 *
 * @return Date with the week of month in parameter
 */
fun Date.with(weekday: Int = -1): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    if (weekday > -1) calendar.set(Calendar.WEEK_OF_MONTH, weekday)
    return calendar.time
}

/**
 * Get the first day of current year
 */
val Date.beginningOfYear: Date
    get() = with(month = 1, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0)

/**
 * Get the last day of the current year
 */
val Date.endOfYear: Date
    get() = with(month = 12, day = 31, hour = 23, minute = 59, second = 59, millisecond = 999)

/**
 * Get the first day of current month
 */
val Date.beginningOfMonth: Date
    get() = with(day = 1, hour = 0, minute = 0, second = 0, millisecond = 0)

/**
 * Get the last day of the current month
 */
val Date.endOfMonth: Date
    get() = endOfMonth()

fun Date.endOfMonth(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val lastDay = calendar.getActualMaximum(Calendar.DATE)
    return with(day = lastDay, hour = 23, minute = 59, second = 59)
}

/**
 * Get the beginning time of the day
 */
val Date.beginningOfDay: Date
    get() = with(hour = 0, minute = 0, second = 0, millisecond = 0)

/**
 * Get the last time of the day
 */
val Date.endOfDay: Date
    get() = with(hour = 23, minute = 59, second = 59, millisecond = 999)

/**
 * Get the first time of the hour
 */
val Date.beginningOfHour: Date
    get() = with(minute = 0, second = 0, millisecond = 0)

/**
 * Get the last time of the hour
 */
val Date.endOfHour: Date
    get() = with(minute = 59, second = 59, millisecond = 999)

/**
 * Get the first time of the minute
 */
val Date.beginningOfMinute: Date
    get() = with(second = 0, millisecond = 0)

/**
 * Get the last time of the minute
 */
val Date.endOfMinute: Date
    get() = with(second = 59, millisecond = 999)

/**
 * Convert date to specific string format
 *
 * @param formatPattern - format date, ex: dd/MM/yyyy
 */
fun Date.toString(formatPattern: String): String = SimpleDateFormat(formatPattern).format(this)

/**
 * Check if the current date is Sunday
 */
val Date.isSunday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
    }

/**
 * Check if the current date is Monday
 */
val Date.isMonday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
    }

/**
 * Check if the current date is Tuesday
 */
val Date.isTuesday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY
    }

/**
 * Check if the current date is Wednesday
 */
val Date.isWednesday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY
    }

/**
 * Check if the current date is Thursday
 */
val Date.isThursday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY
    }

/**
 * Check if the current date is Friday
 */
val Date.isFriday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
    }

/**
 * Check if the current date is Saturday
 */
val Date.isSaturday: Boolean
    get() {
        val calendar = Calendar.getInstance()
        calendar.time = this
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
    }

/**
 * Check if the current date is Weekend
 */
val Date.isWeekend: Boolean
    get() = this.isSaturday || this.isSunday

/**
 * Check if the current date is Weekday
 */
val Date.isWeekday: Boolean
    get() = !this.isWeekend

/**
 * Check if the current date is Today
 */
val Date.isToday: Boolean
    get() = isDateIn(this, 0)

/**
 * Check if the current date is Yesterday
 */
val Date.isYesterday: Boolean
    get() = isDateIn(this, -1)

/**
 * Check if the current date is Tomorrow
 */
val Date.isTomorrow: Boolean
    get() = isDateIn(this, 1)

/**
 * Check if the date is in a specific time
 *
 * @param date - Date want to check
 * @param valueOfDate - the value of date
 *
 * @return value to check if the current date is in expected date
 */
private fun isDateIn(date: Date, valueOfDate: Int = 0): Boolean {
    val now = Calendar.getInstance()
    val cdate = Calendar.getInstance()
    cdate.timeInMillis = date.time

    now.add(Calendar.DATE, valueOfDate)

    return (now.get(Calendar.YEAR) == cdate.get(Calendar.YEAR)
        && now.get(Calendar.MONTH) == cdate.get(Calendar.MONTH)
        && now.get(Calendar.DATE) == cdate.get(Calendar.DATE))
}

/**
 * Convert from String to Date
 */
fun String.toDate(format: String): Date? = try {
    SimpleDateFormat(format).parse(this)
} catch (e: Exception) {
    null
}
