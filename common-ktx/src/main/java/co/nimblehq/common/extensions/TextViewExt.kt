package co.nimblehq.common.extensions

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
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

/**
 * Support partially clickable text on TextView
 *
 * Sample:
 * textview.setClickableText("Click here", object : OnSpanClickListener("here") {
 *     override fun onClick(textView: TextView, span: String) {
 *         // Do something
 *     }
 * })
 */
fun TextView.setClickableText(
    text: CharSequence,
    vararg onLinkClickListener: OnSpanClickListener,
    ignoreCase: Boolean = false
) {
    movementMethod = LinkMovementMethod.getInstance()

    val spannable = SpannableString(text)
    onLinkClickListener.forEach {
        val startIndex = spannable.indexOf(string = it.span, ignoreCase = ignoreCase)
        if (startIndex >= 0) {
            val endIndex = startIndex + it.span.length
            spannable.setSpan(it, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
    this.text = spannable
}

abstract class OnSpanClickListener(val span: String) : ClickableSpan() {

    override fun onClick(widget: View) {
        onClick(widget as TextView, span)
        // Prevent CheckBox state from being toggled when link is clicked
        widget.cancelPendingInputEvents()
    }

    abstract fun onClick(textView: TextView, span: String)

    override fun updateDrawState(ds: TextPaint) {
        // override this method in order to keep predefined text font and color
    }
}
