package co.nimblehq.common.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

inline fun <reified T> Fragment.findCallback(): T? {
    var parent = parentFragment
    while (parent != null) {
        if (parent is T) {
            return parent
        }
        parent = parent.parentFragment
    }

    return activity as? T ?: activity?.application as? T
}

fun Fragment.inTransactionSafe(action: (FragmentTransaction) -> Unit) {
    if (isAdded) {
        childFragmentManager.beginTransaction().apply(action).commit()
    }
}

fun Fragment.inNavigationSafe(action: (View) -> Unit) {
    view?.let { action(it) }
}
