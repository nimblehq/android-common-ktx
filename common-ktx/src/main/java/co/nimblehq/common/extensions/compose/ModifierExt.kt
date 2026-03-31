package co.nimblehq.common.extensions.compose

import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A throttled clickable modifier that prevents multiple clicks in a short period.
 *
 * Usage:
 * ```
 * Button(
 *     modifier = Modifier.throttleClickable { viewModel.submit() }
 * )
 *
 * // With custom interval
 * Button(
 *     modifier = Modifier.throttleClickable(intervalMillis = 1000L) {
 *         viewModel.submit()
 *     }
 * )
 * ```
 *
 * @param enabled Whether the click is enabled.
 * @param intervalMillis The minimum interval between clicks in milliseconds. Default is 500ms.
 * @param onClick The callback to invoke when the click is not throttled.
 *
 * Reference: https://stackoverflow.com/a/72117388/3598447
 */
inline fun Modifier.throttleClickable(
    enabled: Boolean = true,
    intervalMillis: Long = 500L,
    crossinline onClick: () -> Unit,
): Modifier = composed {
    throttleClickable(
        interactionSource = null,
        indication = null,
        enabled = enabled,
        intervalMillis = intervalMillis,
        onClick = onClick,
    )
}

/**
 * A throttled clickable modifier with custom interaction source and indication.
 * Use this when you need to control the ripple effect separately from the clickable area.
 *
 * Usage:
 * ```
 * val interactionSource = remember { MutableInteractionSource() }
 *
 * Box(
 *     modifier = Modifier.throttleClickable(
 *         interactionSource = interactionSource,
 *         indication = ripple(),
 *     ) {
 *         viewModel.submit()
 *     }
 * )
 *
 * // Pass null to skip the ripple effect
 * Box(
 *     modifier = Modifier.throttleClickable(
 *         interactionSource = interactionSource,
 *         indication = null,
 *     ) {
 *         viewModel.submit()
 *     }
 * )
 * ```
 *
 * @param interactionSource The [MutableInteractionSource] to dispatch interaction events to.
 * @param indication The [Indication] to draw when the modifier is interacted with, or null to skip.
 * @param enabled Whether the click is enabled.
 * @param intervalMillis The minimum interval between clicks in milliseconds. Default is 500ms.
 * @param onClick The callback to invoke when the click is not throttled.
 *
 * Reference: https://stackoverflow.com/a/72117388/3598447
 */
inline fun Modifier.throttleClickable(
    interactionSource: MutableInteractionSource?,
    indication: Indication?,
    enabled: Boolean = true,
    intervalMillis: Long = 500L,
    crossinline onClick: () -> Unit,
): Modifier = composed {
    var throttled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    this.clickable(
        interactionSource = interactionSource,
        indication = indication,
        enabled = enabled,
    ) {
        if (!throttled) {
            throttled = true
            onClick()
            coroutineScope.launch {
                delay(intervalMillis)
                throttled = false
            }
        }
    }
}
