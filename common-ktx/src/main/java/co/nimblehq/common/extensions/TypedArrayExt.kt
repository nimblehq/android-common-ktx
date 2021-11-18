package co.nimblehq.common.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.FontRes
import androidx.annotation.StyleableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

/**
 * Unfortunatelly we need to pass context, as there is no proper way to parse font from fonts resource.
 * There is variant to parse raw resource and get it as InputStream, then convert it into file and then pass to Typeface.
 * But it is much easier to pass context.
 */
fun TypedArray.getTypeface(context: Context, @StyleableRes index: Int) =
    runCatching { getFontCompat(context, index) }.getOrNull() ?: getString(index)?.let {
        Typeface.create(
            it,
            Typeface.NORMAL
        )
    }

fun TypedArray.getFontCompat(context: Context, @StyleableRes index: Int): Typeface? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        getFont(index)
    } else {
        context.getFontCompat(getResourceId(index, -1))
    }
}

fun Context.getFontCompat(@FontRes fontId: Int): Typeface? =
    runCatching { ResourcesCompat.getFont(this, fontId) }.getOrNull()

fun TypedArray.getColorStateList(context: Context, @StyleableRes index: Int): ColorStateList? {
    if (hasValue(index)) {
        val resourceId = getResourceId(index, 0)
        if (resourceId != 0) {
            val value = AppCompatResources.getColorStateList(context, resourceId)
            if (value != null) {
                return value
            }
        }
    }
    return getColorStateList(index)
}

fun TypedArray.getDrawable(context: Context, @StyleableRes index: Int): Drawable? {
    if (hasValue(index)) {
        val resourceId = getResourceId(index, 0)
        if (resourceId != 0) {
            val value = AppCompatResources.getDrawable(context, resourceId)
            if (value != null) {
                return value
            }
        }
    }
    return getDrawable(index)
}

