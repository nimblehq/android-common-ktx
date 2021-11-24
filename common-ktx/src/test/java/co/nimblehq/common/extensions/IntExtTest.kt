package co.nimblehq.common.extensions

import co.nimblehq.common.extensions.date.TimeInterval
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class IntExtTest {

    @Test
    private fun `when checking with 1 year, it should be the same with TimeInterval in 1 year`() {
        assertEquals(TimeInterval(unit = Calendar.YEAR, value = 1), 1.year)
    }

    @Test
    private fun `when checking with 5 year, it should be the same with TimeInterval in 5 year`() {
        assertEquals(TimeInterval(unit = Calendar.YEAR, value = 5), 5.years)
    }

    @Test
    private fun `when checking with 1 month, it should be the same with TimeInterval in 0 month`() {
        assertEquals(TimeInterval(unit = Calendar.MONTH, value = 0), 1.month)
    }

    @Test
    private fun `when checking with 3 months, it should be the same with TimeInterval in 2 months`() {
        assertEquals(TimeInterval(unit = Calendar.MONTH, value = 2), 3.months)
    }

    @Test
    private fun `when checking with 1 week, it should be the same with TimeInterval in 1 week`() {
        assertEquals(TimeInterval(unit = Calendar.WEEK_OF_MONTH, value = 1), 1.week)
    }

    @Test
    private fun `when checking with 7 weeks, it should be the same with TimeInterval in 7 weeks`() {
        assertEquals(TimeInterval(unit = Calendar.WEEK_OF_MONTH, value = 7), 7.weeks)
    }

    @Test
    private fun `when checking with 1 day, it should be the same with TimeInterval in 1 day`() {
        assertEquals(TimeInterval(unit = Calendar.DAY_OF_MONTH, value = 1), 1.day)
    }

    @Test
    private fun `when checking with 9 days, it should be the same with TimeInterval in 9 days`() {
        assertEquals(TimeInterval(unit = Calendar.DAY_OF_MONTH, value = 9), 9.days)
    }

    @Test
    private fun `when checking with 1 hour, it should be the same with TimeInterval in 1 hour`() {
        assertEquals(TimeInterval(unit = Calendar.HOUR_OF_DAY, value = 1), 1.hour)
    }

    @Test
    private fun `when checking with 11 hours, it should be the same with TimeInterval in 11 hours`() {
        assertEquals(TimeInterval(unit = Calendar.HOUR_OF_DAY, value = 11), 11.hours)
    }

    @Test
    private fun `when checking with 1 minute, it should be the same with TimeInterval in 1 minute`() {
        assertEquals(TimeInterval(unit = Calendar.MINUTE, value = 1), 1.minute)
    }

    @Test
    private fun `when checking with 13 minutes, it should be the same with TimeInterval in 13 minutes`() {
        assertEquals(TimeInterval(unit = Calendar.MINUTE, value = 13), 13.minutes)
    }

    @Test
    private fun `when checking with 1 second, it should be the same with TimeInterval in 1 second`() {
        assertEquals(TimeInterval(unit = Calendar.SECOND, value = 1), 1.second)
    }

    @Test
    private fun `when checking with 15 seconds, it should be the same with TimeInterval in 15 seconds`() {
        assertEquals(TimeInterval(unit = Calendar.SECOND, value = 15), 15.seconds)
    }

    @Test
    private fun `when checking with 1 millisecond, it should be the same with TimeInterval in 1 millisecond`() {
        assertEquals(TimeInterval(unit = Calendar.MILLISECOND, value = 1), 1.millisecond)
    }

    @Test
    private fun `when checking with 15 milliseconds, it should be the same with TimeInterval in 15 milliseconds`() {
        assertEquals(TimeInterval(unit = Calendar.MILLISECOND, value = 15), 15.milliseconds)
    }
}
