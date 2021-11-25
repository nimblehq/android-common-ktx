package co.nimblehq.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * Request to hide the soft input window from the Activity.
 */
fun Activity.hideSoftKeyboard() {
    currentFocus?.let {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

/**
 * Detect if the activity was launched from Recent Apps List.
 *
 * Reference: https://stackoverflow.com/q/4866149
 */
fun Activity.isActivityLaunchedFromHistory(): Boolean {
    val flags = intent.flags
    return flags and Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY != 0
}

/**
 * Restart the application by start the Activity in new task from recent history.
 *
 * @param clazz Activity class
 */
fun Activity.restartApp(clazz: Class<*>) {
    val intent = Intent(this, clazz).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
    finish()
    Runtime.getRuntime().exit(0)
}

/**
 * Change the status bar color.
 *
 * @param colorResId Color resource id
 */
fun Activity.changeStatusBarColor(@ColorRes colorResId: Int) {
    val window = this.window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, colorResId)
}
