package co.nimblehq.common.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class NumberExtTest {

    @Test
    fun `when calling orZero on a null Int, it should return 0`() {
        val value: Int? = null
        assertEquals(0, value.orZero())
    }

    @Test
    fun `when calling orZero on a non-null Int, it should return its value`() {
        val value = 5
        assertEquals(5, value.orZero())
    }

    @Test
    fun `when calling orZero on a null Long, it should return 0L`() {
        val value: Long? = null
        assertEquals(0L, value.orZero())
    }

    @Test
    fun `when calling orZero on a non-null Long, it should return its value`() {
        val value = 5L
        assertEquals(5L, value.orZero())
    }

    @Test
    fun `when calling orZero on a null Float, it should return 0f`() {
        val value: Float? = null
        assertEquals(0f, value.orZero(), 0f)
    }

    @Test
    fun `when calling orZero on a non-null Float, it should return its value`() {
        val value = 5.5f
        assertEquals(5.5f, value.orZero(), 0f)
    }

    @Test
    fun `when calling orZero on a null Double, it should return 0 point 0`() {
        val value: Double? = null
        assertEquals(0.0, value.orZero(), 0.0)
    }

    @Test
    fun `when calling orZero on a non-null Double, it should return its value`() {
        val value = 5.5
        assertEquals(5.5, value.orZero(), 0.0)
    }

    @Test
    fun `when calling orZero on a null BigDecimal, it should return BigDecimal ZERO`() {
        val value: BigDecimal? = null
        assertEquals(BigDecimal.ZERO, value.orZero())
    }

    @Test
    fun `when calling orZero on a non-null BigDecimal, it should return its value`() {
        val value = BigDecimal("5.5")
        assertEquals(BigDecimal("5.5"), value.orZero())
    }

    @Test
    fun `when calling orDefault on a null Int, it should return the default value`() {
        val value: Int? = null
        assertEquals(10, value.orDefault(10))
    }

    @Test
    fun `when calling orDefault on a non-null Int, it should return its value`() {
        val value = 5
        assertEquals(5, value.orDefault(10))
    }

    @Test
    fun `when calling orDefault on a null Long, it should return the default value`() {
        val value: Long? = null
        assertEquals(10L, value.orDefault(10L))
    }

    @Test
    fun `when calling orDefault on a non-null Long, it should return its value`() {
        val value = 5L
        assertEquals(5L, value.orDefault(10L))
    }

    @Test
    fun `when calling orDefault on a null Float, it should return the default value`() {
        val value: Float? = null
        assertEquals(10f, value.orDefault(10f), 0f)
    }

    @Test
    fun `when calling orDefault on a non-null Float, it should return its value`() {
        val value = 5.5f
        assertEquals(5.5f, value.orDefault(10f), 0f)
    }

    @Test
    fun `when calling orDefault on a null Double, it should return the default value`() {
        val value: Double? = null
        assertEquals(10.0, value.orDefault(10.0), 0.0)
    }

    @Test
    fun `when calling orDefault on a non-null Double, it should return its value`() {
        val value = 5.5
        assertEquals(5.5, value.orDefault(10.0), 0.0)
    }

    @Test
    fun `when calling orDefault on a null BigDecimal, it should return the default value`() {
        val value: BigDecimal? = null
        assertEquals(BigDecimal.TEN, value.orDefault(BigDecimal.TEN))
    }

    @Test
    fun `when calling orDefault on a non-null BigDecimal, it should return its value`() {
        val value = BigDecimal("5.5")
        assertEquals(BigDecimal("5.5"), value.orDefault(BigDecimal.TEN))
    }
}
