package co.nimblehq.common.extensions.date

import java.util.*

/**
 * A range of values of type [Date].
 */
class DateRange(
    override val start: Date,
    override val endInclusive: Date
) : ClosedRange<Date> {
    override fun contains(value: Date) = start < value && value < endInclusive
}
