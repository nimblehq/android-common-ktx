package co.nimblehq.common.extensions

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

/**
 * Handler for managing permission requests in Compose.
 *
 * @param permission The permission string to request.
 * @param isGranted Whether the permission is granted.
 * @param shouldShowRationale Whether to show rationale before requesting.
 * @param isPermanentlyDenied Whether the permission is permanently denied.
 * @param launchRequest Function to launch the permission request.
 * @param openSettings Function to open app settings.
 */
@Immutable
data class PermissionsHandler(
	val permission: String,
	val isGranted: Boolean,
	val shouldShowRationale: Boolean,
	val isPermanentlyDenied: Boolean,
	val launchRequest: () -> Unit,
	val openSettings: (Context) -> Intent,
)

/**
 * Remember a permission handler for managing permission requests.
 *
 * @param permission The permission string to request.
 * @param onPermissionResult Callback invoked when permission result changes.
 *
 * @return A PermissionsHandler instance for managing the permission.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberPermissionHandler(
	permission: String,
	onPermissionResult: ((Boolean) -> Unit)? = null,
): PermissionsHandler {
	val permissionState = rememberPermissionState(permission = permission)
	
	val isPermanentlyDenied = rememberPermanentDenialTracking(
		isGranted = permissionState.status.isGranted,
		shouldShowRationale = permissionState.status.shouldShowRationale,
	)
	
	onPermissionResult?.let { callback ->
		LaunchedEffect(permissionState.status.isGranted) {
			callback(permissionState.status.isGranted)
		}
	}
	
	return remember(
		permission,
		permissionState.status.isGranted,
		permissionState.status.shouldShowRationale,
		isPermanentlyDenied,
	) {
		PermissionsHandler(
			permission = permission,
			isGranted = permissionState.status.isGranted,
			shouldShowRationale = permissionState.status.shouldShowRationale,
			isPermanentlyDenied = isPermanentlyDenied,
			launchRequest = permissionState::launchPermissionRequest,
			openSettings = { context ->
				context.getAppSettingsIntent(context.packageName)
			},
		)
	}
}

/**
 * Handle permission requests with callbacks for granted and denied states.
 *
 * @param permission The permission string to request.
 * @param onGranted Callback invoked when permission is granted.
 * @param onDenied Callback invoked when permission is denied.
 * @param content Content to display with the permission handler.
 */
@Composable
fun HandlePermissionsRequest(
	permission: String,
	onGranted: () -> Unit,
	onDenied: (isPermanent: Boolean) -> Unit = {},
	content: @Composable (handler: PermissionsHandler) -> Unit,
) {
	val handler = rememberPermissionHandler(permission = permission)
	
	LaunchedEffect(handler.isGranted, handler.isPermanentlyDenied) {
		when {
			handler.isGranted -> onGranted()
			handler.isPermanentlyDenied -> onDenied(true)
			handler.shouldShowRationale -> onDenied(false)
		}
	}
	
	content(handler)
}

/**
 * Track if permission is permanently denied.
 *
 * @param isGranted Whether the permission is granted.
 * @param shouldShowRationale Whether to show rationale.
 *
 * @return True if permission is permanently denied.
 */
@Stable
@Composable
private fun rememberPermanentDenialTracking(
	isGranted: Boolean,
	shouldShowRationale: Boolean,
): Boolean {
	var previousShouldShowRationale by rememberSaveable { mutableStateOf(shouldShowRationale) }
	var rationaleTransitionedToFalse by rememberSaveable { mutableStateOf(false) }
	
	LaunchedEffect(shouldShowRationale, isGranted) {
		// Permanent denial: rationale was true, now false, and permission not granted
		if (previousShouldShowRationale && !shouldShowRationale && !isGranted) {
			rationaleTransitionedToFalse = true
		}
		
		previousShouldShowRationale = shouldShowRationale
		
		if (isGranted) {
			rationaleTransitionedToFalse = false
		}
	}
	
	return rationaleTransitionedToFalse && !isGranted
}
