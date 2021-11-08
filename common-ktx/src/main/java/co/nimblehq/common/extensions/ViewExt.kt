package co.nimblehq.common.extensions

import android.graphics.*
import android.os.Handler
import android.view.View
import android.widget.ScrollView
import androidx.annotation.DimenRes

private const val COLLAPSED_STATE_ROTATION = 180f
private const val EXPANDED_STATE_ROTATION = 0f

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

fun View.convertPxToSp(px: Int): Float {
    return px / resources.displayMetrics.scaledDensity
}

fun View.convertSpToPx(sp: Float): Int {
    return (sp * resources.displayMetrics.scaledDensity).toInt()
}

fun View.getBitmap(height: Int, width: Int): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val bgDrawable = this.background
    if (bgDrawable != null)
        bgDrawable.draw(canvas)
    else
        canvas.drawColor(Color.WHITE)
    this.draw(canvas)
    return bitmap
}
