package co.nimblehq.common.extensions.date

import co.nimblehq.common.extensions.days
import co.nimblehq.common.extensions.years
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeIntervalTest {

    @Test
    fun `when compare yesterday and today with 5 days ago, it should be the same`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -5)

        val fiveDaysAgo = 5.days.ago
        val calendar2 = Calendar.getInstance()
        calendar2.time = fiveDaysAgo

        assertEquals(calendar.get(Calendar.DATE), calendar2.get(Calendar.DATE))
        assertEquals(calendar.get(Calendar.MONTH), calendar2.get(Calendar.MONTH))
        assertEquals(calendar.get(Calendar.YEAR), calendar2.get(Calendar.YEAR))
    }

    @Test
    fun `when forward 5 years from today, it should the same with next five year`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 5)

        val nextFiveYears = 5.years.forward
        val calendar2 = Calendar.getInstance()
        calendar2.time = nextFiveYears

        assertEquals(calendar.get(Calendar.DATE), calendar2.get(Calendar.DATE))
        assertEquals(calendar.get(Calendar.MONTH), calendar2.get(Calendar.MONTH))
        assertEquals(calendar.get(Calendar.YEAR), calendar2.get(Calendar.YEAR))
    }
}

