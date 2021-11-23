package co.nimblehq.common.extensions

import android.widget.TextView
import androidx.core.text.HtmlCompat

/**
 * Clear TextView in shorter call
 */
fun TextView.clear() {
    this.text = ""
}

/**
 * Display Html string and keep the style from the Html script
 *
 * @param htmlText A text with Html tag
 */
fun TextView.setHtmlText(htmlText: String) {
    this.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
