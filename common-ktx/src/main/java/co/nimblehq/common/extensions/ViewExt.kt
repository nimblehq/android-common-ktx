package co.nimblehq.common.extensions

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.Px
import java.lang.ref.WeakReference

/**
 * Make view visible.
 */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * Make view invisible.
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Make view gone.
 */
fun View.gone() {
    this.visibility = View.GONE
}

/**
 * Check if the visible condition is true; set view visible; otherwise, set view gone.
 *
 * @param visible The condition to make view visible.
 */
fun View.visibleOrGone(visible: Boolean) {
    if (visible) visible() else gone()
}

/**
 * Check if the visible condition is true; set view visible; otherwise, set view gone.
 * The visibility will active after the delay.
 *
 * @param visible The condition to make view visible.
 * @param delay The delay time in millisecond to affect the view.
 */
fun View.visibleOrGone(visible: Boolean, delay: Long) {
    if (delay <= 0L) {
        visibleOrGone(visible)
    } else {
        postDelayed({
            val context: WeakReference<Context?>? = WeakReference(this.context)
            if (context?.get() is Activity &&
                (context?.get() as Activity).isFinishing) {
                return@postDelayed
            }
            visibleOrGone(visible)
        }, delay)
    }
}

/**
 * Check if the visible condition is true; set view visible; otherwise, set view invisible.
 *
 * @param visible The condition to make view visible.
 */
fun View.visibleOrInvisible(visible: Boolean) {
    if (visible) visible() else invisible()
}

/**
 * Check if the view is visible then invoke the listener.
 *
 * @param listener The function would be called after checking view is visible.
 */
inline fun View.ifVisibleThen(listener: (View) -> Unit) {
    if (this.visibility == View.VISIBLE) {
        listener.invoke(this)
    }
}

/**
 * Set bottom padding.
 *
 * @param dimenRes The dimension resource id.
 */
fun View.setBottomPaddingRes(@DimenRes dimenRes: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, resources.getDimensionPixelSize(dimenRes))
}

/**
 * Set top padding.
 *
 * @param dimenRes The dimension resource id.
 */
fun View.setTopPaddingRes(@DimenRes dimenRes: Int) {
    setPadding(paddingLeft, resources.getDimensionPixelSize(dimenRes), paddingRight, paddingBottom)
}

/**
 * Enable view when the condition is matched.
 *
 * Samples: btNext.enableWhen{ userName.isNotEmpty() && password.isNotEmpty() }
 *
 * @param predicate The condition to enable the view.
 */
fun View.enableWhen(predicate: () -> Boolean) {
    this.isEnabled = predicate()
}

/**
 * Convert pixel to Sp for Font sizing usage.
 *
 * @param px The pixel amount.
 *
 * @return value in Sp unit
 */
fun View.convertPxToSp(@Px px: Int): Float {
    return px / resources.displayMetrics.scaledDensity
}

/**
 * Convert Sp to pixel.
 *
 * @param sp The Sp amount.
 *
 * @return value in Dp unit
 */
fun View.convertSpToPx(sp: Float): Int {
    return (sp * resources.displayMetrics.scaledDensity).toInt()
}

/**
 * Return bitmap with specific size.
 *
 * @param resultHeight Height of bitmap result.
 * @param resultWidth Width of bitmap result.
 *
 * @return bitmap with given height and width
 */
@Throws(java.lang.IllegalArgumentException::class)
fun View.getBitmap(resultHeight: Int, resultWidth: Int): Bitmap {
    if (resultHeight <= 0 || resultWidth <= 0) throw IllegalArgumentException("Invalid arguments")

    val bitmap = Bitmap.createBitmap(resultWidth, resultHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val bgDrawable = this.background

    if (bgDrawable != null) {
        bgDrawable.draw(canvas)
    } else {
        canvas.drawColor(Color.WHITE)
    }

    this.draw(canvas)
    return bitmap
}
