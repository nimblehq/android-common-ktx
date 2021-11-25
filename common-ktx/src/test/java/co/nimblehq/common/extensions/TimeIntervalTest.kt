package co.nimblehq.common.extensions

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeIntervalTest {

    @Test
    private fun `when compare yesterday and today with 1 day ago, it should be the same`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val yesterday = calendar.time
        assertEquals(yesterday, 1.days.ago)
    }

    @Test
    private fun `when forward 5 years from today, it should the same with five year later`() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 5)
        val fiveYearsLater = calendar.time
        assertEquals(fiveYearsLater, 5.years.forward)
    }
}

