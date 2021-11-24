package co.nimblehq.common.extensions

import org.junit.Assert
import org.junit.Test
import java.util.*

class DateRangeTest {

    @Test
    fun `when checking date in range, it should return the correct answer`() {
        Assert.assertTrue(1.day.ago in 2.days.ago..Date())
        Assert.assertTrue(3.days.ago !in 2.days.ago..Date())
        Assert.assertTrue(Date() in 2.day.ago..2.days.forward)
    }
}
