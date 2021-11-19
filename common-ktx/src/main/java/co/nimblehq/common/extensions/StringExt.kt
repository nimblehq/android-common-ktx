package co.nimblehq.common.extensions

import android.util.Patterns.EMAIL_ADDRESS
import java.util.*

/**
 * Check if this string not null or empty.
 * This extension wraps for more readable.
 */
fun String?.isNotNullOrEmpty(): Boolean = !this.isNullOrEmpty()

/**
 * Check if this string not null or blank.
 * This extension wraps for more readable.
 */
fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

/**
 * Eliminate the given character then titleize.
 *
 * @param delimiter The character that would like to eliminate.
 */
fun String.titleize(delimiter: String): String {
    return this.split(delimiter)
        .joinToString(" ", transform = { s ->
            s.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        })
}

/**
 * Transform the string with spaces to snake case.
 */
fun String.spaceToSnakeCase(): String {
    return this.split(" ")
        .joinToString("_", transform = { it.lowercase(Locale.getDefault()) })
}

/**
 * Check if this string is matched with the email pattern.
 */
fun String.isEmailValid(): Boolean {
    return isNotEmpty() && EMAIL_ADDRESS.matcher(this).matches()
}
