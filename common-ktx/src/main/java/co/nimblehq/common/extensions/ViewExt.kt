package co.nimblehq.common.extensions

import android.view.View
import androidx.annotation.DimenRes

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibleOrGone(visible: Boolean, delay: Long) {
    if (delay <= 0L) {
        visibleOrGone(visible)
    } else {
        postDelayed({ visibleOrGone(visible) }, delay)
    }
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) visible() else gone()
}

fun View.visibleOrInvisible(visible: Boolean) {
    if (visible) visible() else invisible()
}

inline fun View.ifVisibleThen(listener: (View) -> Unit) {
    if (this.visibility == View.VISIBLE) {
        listener.invoke(this)
    }
}

fun View.setBottomPaddingRes(@DimenRes dimenRes: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, resources.getDimensionPixelSize(dimenRes))
}

fun View.setTopPaddingRes(@DimenRes dimenRes: Int) {
    setPadding(paddingLeft, resources.getDimensionPixelSize(dimenRes), paddingRight, paddingBottom)
}

fun View.setTopPadding(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, paddingBottom)
}

fun View.enableWhen(predicate: () -> Boolean) {
    this.isEnabled = predicate()
}
