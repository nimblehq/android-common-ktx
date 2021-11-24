package co.nimblehq.common.extensions

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateExtTest {

    @Test
    private fun `when calling with plus function, the date should move forward to the target`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_MONTH, 1)
        val nextWeek = calendar.time
        assertEquals(nextWeek, Date() + 1.week)
    }

    @Test
    private fun `when calling with minus function, the date should move backward to the target`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -2)
        val dayBeforeYesterday = calendar.time
        assertEquals(dayBeforeYesterday, Date() - 2.days)
    }

    @Test
    private fun `when calling with function, the date properties will be assigned with the exact value`() {
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
    private fun `when calling beginningOfYear function, it should return the first day of the year`() {
        val date = Date().with(year = 2016, month = 6, day = 2, hour = 5, minute = 30, second = 0)
        assertEquals(
            Date().with(year = 2016, month = 1, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0),
            date.beginningOfYear
        )
    }

    @Test
    private fun `when calling enOfYear function, it should return the last day of the year`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 5, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 12, day = 31, hour = 23, minute = 59, second = 59, millisecond = 999),
            date.endOfYear
        )
    }

    @Test
    private fun `when calling beginningOfMonth function, it should return the first day of the month`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 5, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0),
            date.beginningOfMonth
        )
    }

    @Test
    private fun `when calling endOfMonth function, it should return the last day of the month`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 30, hour = 23, minute = 59, second = 59),
            date.endOfMonth
        )
    }

    @Test
    private fun `when calling beginningOfDay function, it should return the first time of the day`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 0, minute = 0, second = 0, millisecond = 0),
            date.beginningOfDay
        )
    }

    @Test
    private fun `when calling endOfDay function, it should return the last time of the day`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 9, minute = 0, second = 0)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 23, minute = 59, second = 59, millisecond = 999),
            date.endOfDay
        )
    }

    @Test
    private fun `when calling beginningOfHour function, it should return the first time of hour`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 0, second = 0, millisecond = 0),
            date.beginningOfHour
        )
    }

    @Test
    private fun `when calling endOfHour function, it should return the last time of hour`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 59, second = 59, millisecond = 999),
            date.endOfHour
        )
    }

    @Test
    private fun `when calling beginningOfMinute, it should return the first time of minute`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 0, millisecond = 0),
            date.beginningOfMinute
        )
    }

    @Test
    private fun `when calling endOfMinute, it should return the last time of minute`() {
        val date = Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 30)
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 12, minute = 30, second = 59, millisecond = 999),
            date.endOfMinute
        )
    }

    @Test
    private fun `when calling toString function with specific date format, it should return the corresponding string`() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 5)
        assertEquals(
            SimpleDateFormat("yyyy-MM-dd HH").format(calendar.time),
            5.minutes.forward.toString("yyyy-MM-dd HH")
        )
    }

    @Test
    private fun `when calling isSunday function, it should return the exact value`() {
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
    private fun `when calling isMonday function, it should return the exact value`() {
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
    private fun `when calling isTuesday function, it should return the exact value`() {
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
    private fun `when calling isWednesday function, it should return the exact value`() {
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
    private fun `when calling isThursday function, it should return the exact value`() {
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
    private fun `when calling isFriday function, it should return the exact value`() {
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
    private fun `when calling isSaturday function, it should return the exact value`() {
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
    private fun `when calling isToday function, it should return the exact value`() {
        val date = Date()
        assertTrue(date.isToday)
    }

    @Test
    private fun `when calling isYesterday function, it should return the exact value`() {
        val date = Date() - 1.day
        assertTrue(date.isYesterday)
    }

    @Test
    private fun `when calling isTomorrow function, it should return the exact value`() {
        val date = tomorrow
        assertTrue(date.isTomorrow)
    }

    @Test
    private fun `when calling isWednesday is true, it should return the exact value, and it should not be a weekend, weekday`() {
        val date = Date().with(year = 2019, month = 6, day = 12)
        assertTrue(date.isWednesday)
        assertFalse(date.isWeekend)
        assertTrue(date.isWeekday)
    }

    @Test
    private fun `when calling isThursday is true, it should return the exact value, and it should not be a weekend, weekday but not a Wednesday for example`() {
        val date = Date().with(year = 2019, month = 6, day = 13)
        assertFalse(date.isWednesday)
        assertTrue(date.isThursday)
        assertFalse(date.isWeekend)
        assertTrue(date.isWeekday)
    }

    @Test
    private fun `when calling isSaturday is true, it should return the exact value, and it should be a weekend, but not a Wednesday or weekday for example`() {
        val date = Date().with(year = 2019, month = 6, day = 15)
        assertFalse(date.isWednesday)
        assertTrue(date.isSaturday)
        assertTrue(date.isWeekend)
        assertFalse(date.isWeekday)
    }

    @Test
    private fun `when calling isSunday is true, it should return the exact value, and it should be a weekend, but not a Wednesday or weekday for example`() {
        val date = Date().with(year = 2019, month = 6, day = 15)
        assertFalse(date.isWednesday)
        assertTrue(date.isSunday)
        assertTrue(date.isWeekend)
        assertFalse(date.isWeekday)
    }

    @Test
    private fun `when compare two date, use can use the operator same as an integer`() {
        run {
            assertTrue(1.day.ago < Date())
        }
        run {
            assertTrue(1.day.forward > Date())
        }
    }

    @Test
    private fun `when checking date in range, you can use the operator same as an integer`() {
        run {
            assertTrue(1.day.ago > 2.days.ago)
        }
        run {
            assertTrue(1.day.ago in 2.days.ago..Date())
        }
    }

    @Test
    private fun `when calling toDate function with a specific format string, it should return the value same with toString function`() {
        assertEquals(
            Date().with(year = 1987, month = 6, day = 2, hour = 0, minute = 0, second = 0).toString(),
            "1987-06-02".toDate("yyyy-MM-dd").toString()
        )
    }
}
