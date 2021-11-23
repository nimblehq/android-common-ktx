package co.nimblehq.common.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

/**
 * Get string by a given resource ID.
 *
 * @param resId String resource id
 * @return A string holding the name of the resource.
 */
fun Context.getResourceName(@IdRes resId: Int?): String? =
    resId?.let { resources.getResourceName(resId) }

/**
 * Get color associated with a particular resource ID.
 *
 * @param resId Color resource id
 * @return A single color value in the form 0xAARRGGBB.
 */
fun Context.getColor(@ColorRes resId: Int?): Int? =
    resId?.let { ContextCompat.getColor(this, resId) }

/**
 * Get drawable associated with a particular resource ID.
 *
 * @param resId Drawable resource id
 * @return An object that can be used to draw this resource.
 */
fun Context.getColorDrawable(@DrawableRes resId: Int?): Drawable? =
    resId?.let { ContextCompat.getDrawable(this, resId) }

/**
 * Request to show soft input window by provided View.
 *
 * @param view The currently focused view, which would like to receive soft keyboard input.
 */
fun Context.showSoftKeyboard(view: View) {
    view.requestFocus()
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Provide the intent to open PlayStore with [packageName].
 *
 * @param packageName The application's package name.
 * @return Return the deeplink to open PlayStore.
 */
@SuppressLint("QueryPermissionsNeeded")
fun Context.openPlayStore(packageName: String): Intent {
    val intent = Intent(Intent.ACTION_VIEW, "market://details?id=$packageName".toUri())
    if (intent.resolveActivity(packageManager) == null) {
        intent.data = "https://play.google.com/store/apps/details?id=$packageName".toUri()
    }
    return intent
}

/**
 * Check if application with provided [packageName] is installed on the device.
 *
 * @param packageName The application's package name.
 * @return Return true if application is installed and otherwise.
 */
@SuppressLint("QueryPermissionsNeeded")
fun Context.checkAppInstalled(packageName: String): Boolean {
    return packageManager
        .getInstalledApplications(PackageManager.GET_META_DATA)
        .find { it.packageName == packageName } != null
}

/**
 * Provide the intent to App's Settings screen with [packageName].
 *
 * @param packageName The application's package name.
 * @return Return the deeplink to open App's Settings screen.
 */
fun Context.getAppSettingsIntent(packageName: String): Intent {
    return Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.fromParts("package", packageName, null)
    }
}
