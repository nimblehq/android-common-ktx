package co.nimblehq.common.extensions

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Provide extension functions to Activity/Fragment to capable of showing Toast in a shorter call
 *
 * @param message A message to display
 * @param duration Duration time to display message
 */
fun Activity.toastShort(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toastShort(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun Activity.toastLong(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.toastLong(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, message, duration).show()
}

/**
 * Provide extension functions to Activity to capable of showing Toast with layout
 *
 * Sample:
 *     toastWithLayout(
 *         message = "message",
 *         layoutRes = R.layout.view_pin_toast_error,
 *         rootLayoutRes = R.id.toastRootLayout,
 *         textViewId = R.id.tvErrorMessage
 *     )
 *
 * @param message A message to display
 * @param layoutRes Layout resource id of the layout
 * @param rootLayoutRes Id of the root ViewGroup
 * @param textViewId Id of TextView that will be set message
 */
fun Activity.toastWithLayout(
    message: CharSequence,
    @LayoutRes layoutRes: Int,
    @IdRes rootLayoutRes: Int,
    @IdRes textViewId: Int
) {
    val layout = LayoutInflater.from(this).inflate(
        layoutRes,
        findViewById(rootLayoutRes)
    )
    val textView = layout.findViewById<TextView>(textViewId)
    textView.text = message
    with(Toast(applicationContext)) {
        setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}
