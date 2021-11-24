package co.nimblehq.common.extensions

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

/**
 * Unit test for DateExtensions.kt.
 */
class DateExtensionsTest {

    @Test
    fun plus() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_MONTH, 1)
        val nextWeek = calendar.time
        assertEquals(nextWeek, Date() + 1.week)
    }

    @Test
    fun minus() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -2)
        val dayBeforeYesterday = calendar.time
        assertEquals(dayBeforeYesterday, Date() - 2.days)
    }

    @Test
    fun with() {
        run {
            val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0)
            assertEquals(
                Date().with(year = 1990, month = 6, day = 2, hour = 12, minute = 0, second = 0),
                date.with(year = 1990)
            )
        }
        run {
            val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0)
            assertEquals(
                Date().with(year = 1987, month = 6, day = 9, hour = 12, minute = 0, second = 0),
                date.with(weekday = 2)
            )
        }
    }

    @Test
    fun beginningOfYear() {
        val date = Date().with(year = 2016, month = 6, day = 2, hour = 5, minute = 30, second = 0)
        assertEquals(
            Date().with(year = 2016, month = 1, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0),
            date.beginningOfYear
        )
    }

    @Test
    fun endOfYear() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 5, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 12, day = 31, hour = 23, minute = 59, second = 59, millisecond = 999),
            date.endOfYear
        )
    }

    @Test
    fun beginningOfMonth() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 5, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0),
            date.beginningOfMonth
        )
    }

    @Test
    fun endOfMonth() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 30, hour = 23, minute = 59, second = 59),
            date.endOfMonth
        )
    }

    @Test
    fun beginningOfDay() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 0, minute = 0, second = 0, millisecond = 0),
            date.beginningOfDay
        )
    }

    @Test
    fun endOfDay() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 9, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 23, minute = 59, second = 59, millisecond = 999),
            date.endOfDay
        )
    }

    @Test
    fun beginningOfHour() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0, millisecond = 0),
            date.beginningOfHour
        )
    }

    @Test
    fun endOfHour() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 59, second = 59, millisecond = 999),
            date.endOfHour
        )
    }

    @Test
    fun beginningOfMinute() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 0, millisecond = 0),
            date.beginningOfMinute
        )
    }

    @Test
    fun endOfMinute() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 59, millisecond = 999),
            date.endOfMinute
        )
    }

    @Test
    fun to_String() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5)
        assertEquals(
            SimpleDateFormat("yyyy-MM-dd HH").format(calendar.time),
            5.minutes.forward.toString("yyyy-MM-dd HH")
        )
    }

    @Test
    fun isSunday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 21)
            val date = calendar.time
            assert(date.isSunday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 22)
            val date = calendar.time
            assert(!date.isSunday)
        }
    }

    @Test
    fun isMonday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 22)
            val date = calendar.time
            assert(date.isMonday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 23)
            val date = calendar.time
            assert(!date.isMonday)
        }
    }

    @Test
    fun isTuesday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 23)
            val date = calendar.time
            assert(date.isTuesday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 24)
            val date = calendar.time
            assert(!date.isTuesday)
        }
    }

    @Test
    fun isWednesday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 24)
            val date = calendar.time
            assert(date.isWednesday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 25)
            val date = calendar.time
            assert(!date.isWednesday)
        }
    }

    @Test
    fun isThursday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 25)
            val date = calendar.time
            assert(date.isThursday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 26)
            val date = calendar.time
            assert(!date.isThursday)
        }
    }

    @Test
    fun isFriday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 26)
            val date = calendar.time
            assert(date.isFriday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 27)
            val date = calendar.time
            assert(!date.isFriday)
        }
    }

    @Test
    fun isSaturday() {
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 27)
            val date = calendar.time
            assert(date.isSaturday)
        }
        run {
            val calendar = Calendar.getInstance()
            calendar.set(2016, 1, 28)
            val date = calendar.time
            assert(!date.isSaturday)
        }
    }

    @Test
    fun `is date today?`() {
        val date = Date()
        assertTrue(date.isToday)
    }

    @Test
    fun `is date yesterday?`() {
        val date = Date() - 1.day
        assertTrue(date.isYesterday)
    }

    @Test
    fun `is date tomorrow?`() {
        val date = tomorrow
        assertTrue(date.isTomorrow)
    }

    @Test
    fun `is a Wednesday date a weekday?`() {
        val date = Date().with(year = 2019, month = 6, day = 12)
        assertTrue(date.isWednesday)
        assertFalse(date.isWeekend)
        assertTrue(date.isWeekday)
    }

    @Test
    fun `is a Thursday date a weekday?`() {
        val date = Date().with(year = 2019, month = 6, day = 13)
        assertFalse(date.isWednesday)
        assertTrue(date.isThursday)
        assertFalse(date.isWeekend)
        assertTrue(date.isWeekday)
    }

    @Test
    fun `is a Saturday date a weekend?`() {
        val date = Date().with(year = 2019, month = 6, day = 15)
        assertFalse(date.isWednesday)
        assertTrue(date.isSaturday)
        assertTrue(date.isWeekend)
        assertFalse(date.isWeekday)
    }

    @Test
    fun `is a Sunday date a weekend?`() {
        val date = Date().with(year = 2019, month = 6, day = 15)
        assertFalse(date.isWednesday)
        assertTrue(date.isSaturday)
        assertTrue(date.isWeekend)
        assertFalse(date.isWeekday)
    }

    @Test
    fun compareTo() {
        run {
            assertTrue(1.day.ago < Date())
        }
        run {
            assertTrue(1.day.forward > Date ())
        }
    }

    @Test
    fun `compare inside ranges`() {
        run {
            assertTrue(1.day.ago > 2.days.ago)
        }
        run {
            assertTrue(1.day.ago in 2.days.ago..Date())
        }
    }

    @Test
    fun toDate() {
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 0, minute = 0, second = 0),
            "1987-06-02".toDate("yyyy-MM-dd")
        )
    }
}
