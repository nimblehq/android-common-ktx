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
 * getTypeface from Compat first, if could nof found, then try to create with the family name
 *
 * @param context - Android context
 * @param index - StyleableRes index, sample: R.styleable.your_custom_attribute
 *
 * @return Typeface or null
 */
fun TypedArray.getTypeface(context: Context, @StyleableRes index: Int) =
    runCatching { getFontCompat(context, index) }.getOrNull() ?: getString(index)?.let {
        Typeface.create(
            it,
            Typeface.NORMAL
        )
    }

/**
 * get Compat Font from Styleable Resource
 *
 * @param context - Android context
 * @param index - StyleableRes index, sample: R.styleable.your_custom_attribute
 *
 * @return Typeface Compat or null
 */
fun TypedArray.getFontCompat(context: Context, @StyleableRes index: Int): Typeface? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        getFont(index)
    } else {
        context.getFontCompat(getResourceId(index, -1))
    }
}

/**
 * Get Compat Font from Font Resource
 *
 * @param fontId - font id in Font Resource
 *
 * @return Typeface Compat or null
 */
fun Context.getFontCompat(@FontRes fontId: Int): Typeface? =
    runCatching { ResourcesCompat.getFont(this, fontId) }.getOrNull()

/**
 * Get Color State List from Styleable Resource
 *
 * @paramc context - Android context
 * @param index - Styleable Resource index, sample: R.styleable.your_custom_attribute
 */
fun TypedArray.getColorStateList(context: Context, @StyleableRes index: Int): ColorStateList? {
    val colorStateList = if (hasValue(index)) {
        getResourceId(index, 0)
            .takeIf { resourceId -> resourceId != 0 }
            ?.let { resourceId -> AppCompatResources.getColorStateList(context, resourceId) }
    } else {
        null
    }

    return colorStateList ?: getColorStateList(index)
}

/**
 * Get Drawable from Styleable Resource
 *
 * @paramc context - Android context
 * @param index - Styleable Resource index, sample: R.styleable.your_custom_attribute
 */
fun TypedArray.getDrawable(context: Context, @StyleableRes index: Int): Drawable? {
    val drawable = if (hasValue(index)) {
        getResourceId(index, 0)
            .takeIf { resourceId -> resourceId != 0 }
            ?.let { resourceId -> AppCompatResources.getDrawable(context, resourceId) }
    } else {
        null
    }
    return drawable ?: getDrawable(index)
}
