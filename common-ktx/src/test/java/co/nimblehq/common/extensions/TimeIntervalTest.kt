package co.nimblehq.common.extensions

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeIntervalTest {

    @Test
    fun ago() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        val yesterday = calendar.time
        assertEquals(yesterday, 1.days.ago)
    }

    @Test
    fun later() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 5)
        val fiveYearsLater = calendar.time
        assertEquals(fiveYearsLater, 5.years.forward)
    }
}

