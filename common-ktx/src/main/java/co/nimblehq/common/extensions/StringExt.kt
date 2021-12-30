package co.nimblehq.common.extensions

import androidx.core.util.PatternsCompat
import java.util.*

/**
 * Check if the string not null or empty.
 * This extension wraps for more readable.
 *
 * @return true if this nullable char sequence is NOT either null or empty
 */
fun String?.isNotNullOrEmpty(): Boolean = !this.isNullOrEmpty()

/**
 * Check if the string not null or blank.
 * This extension wraps for more readable.
 *
 * @return true if this nullable char sequence is NOT either null or empty or consists solely of
 * whitespace
 */
fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

/**
 * Eliminate the given character then titleize.
 *
 * @param delimiter The character that would like to eliminate.
 *
 * Samples: Input "example_string" Output: "Example String"
 *
 * @return formatted string
 */
fun String.titleize(delimiter: String): String {
    return this.split(delimiter)
        .joinToString(
            " ",
            transform = { s ->
                s.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            }
        )
}

/**
 * Transform the string with spaces to snake case.
 *
 * Samples: Input "Example String" Output: "example_string"
 *
 * @return formatted string
 */
fun String.spaceToSnakeCase(): String {
    return this.split(" ")
        .joinToString("_", transform = { it.lowercase(Locale.getDefault()) })
}

/**
 * Check if the string is matched with the email pattern.
 *
 * @return true if this string is in the correct email format
 */
fun String.isEmailValid(): Boolean {
    return isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}

/**
 * Check if the string is Thai.
 *
 * @return true if this string is Thai
 */
fun String.isThai(): Boolean {
    val thaiRegex = "([\\u0E00-\\u0E7F]+)".toRegex()
    return if (isEmpty() || isBlank()) false else all { thaiRegex.matches(it.toString()) }
}
