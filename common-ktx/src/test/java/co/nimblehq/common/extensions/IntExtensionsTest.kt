package co.nimblehq.common.extensions

import co.nimblehq.common.extensions.date.TimeInterval
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Unit test for IntExtensions.
 */
class IntExtensionsTest {

    @Test
    fun year() {
        assertEquals(TimeInterval(unit = Calendar.YEAR, value = 1), 1.year)
    }

    @Test
    fun years() {
        assertEquals(TimeInterval(unit = Calendar.YEAR, value = 5), 5.years)
    }

    @Test
    fun month() {
        assertEquals(TimeInterval(unit = Calendar.MONTH, value = 0), 1.month)
    }

    @Test
    fun months() {
        assertEquals(TimeInterval(unit = Calendar.MONTH, value = 2), 3.months)
    }

    @Test
    fun week() {
        assertEquals(TimeInterval(unit = Calendar.WEEK_OF_MONTH, value = 1), 1.week)
    }

    @Test
    fun weeks() {
        assertEquals(TimeInterval(unit = Calendar.WEEK_OF_MONTH, value = 7), 7.weeks)
    }

    @Test
    fun day() {
        assertEquals(TimeInterval(unit = Calendar.DAY_OF_MONTH, value = 1), 1.day)
    }

    @Test
    fun days() {
        assertEquals(TimeInterval(unit = Calendar.DAY_OF_MONTH, value = 9), 9.days)
    }

    @Test
    fun hour() {
        assertEquals(TimeInterval(unit = Calendar.HOUR_OF_DAY, value = 1), 1.hour)
    }

    @Test
    fun hours() {
        assertEquals(TimeInterval(unit = Calendar.HOUR_OF_DAY, value = 11), 11.hours)
    }

    @Test
    fun minute() {
        assertEquals(TimeInterval(unit = Calendar.MINUTE, value = 1), 1.minute)
    }

    @Test
    fun minutes() {
        assertEquals(TimeInterval(unit = Calendar.MINUTE, value = 13), 13.minutes)
    }

    @Test
    fun second() {
        assertEquals(TimeInterval(unit = Calendar.SECOND, value = 1), 1.second)
    }

    @Test
    fun seconds() {
        assertEquals(TimeInterval(unit = Calendar.SECOND, value = 15), 15.seconds)
    }

    @Test
    fun millisecond() {
        assertEquals(TimeInterval(unit = Calendar.MILLISECOND, value = 1), 1.millisecond)
    }

    @Test
    fun milliseconds() {
        assertEquals(TimeInterval(unit = Calendar.MILLISECOND, value = 15), 15.milliseconds)
    }
}
