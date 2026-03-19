package co.nimblehq.extensions

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.nimblehq.common.extensions.HandlePermissionsRequest
import co.nimblehq.common.extensions.PermissionsHandler
import co.nimblehq.common.extensions.rememberPermissionHandler

@Composable
internal fun SamplePermissionScreen() {
    val context = LocalContext.current
    val grantedMessage = stringResource(R.string.location_permission_callback_granted)
    val deniedPermanentMessage = stringResource(R.string.location_permission_callback_denied_permanent)
    val deniedTemporaryMessage = stringResource(R.string.location_permission_callback_denied_temporary)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.permission_demo_title),
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // rememberPermissionHandler example
        CameraPermissionSection()

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(24.dp))

        // HandlePermissionsRequest example
        LocationPermissionSection(
            onGranted = {
                Toast.makeText(context, grantedMessage, Toast.LENGTH_SHORT).show()
            },
            onDenied = { isPermanent ->
                val message = if (isPermanent) deniedPermanentMessage else deniedTemporaryMessage
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            },
        )
    }
}

@Composable
private fun CameraPermissionSection() {
    val handler = rememberPermissionHandler(permission = Manifest.permission.CAMERA)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.camera_permission_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = stringResource(R.string.camera_permission_handler_example),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(4.dp))

        CameraPermissionContent(handler = handler)
    }
}

@Composable
private fun CameraPermissionContent(
    handler: PermissionsHandler,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        when {
            handler.isGranted -> {
                Text(text = stringResource(R.string.camera_permission_granted))
            }
            handler.isPermanentlyDenied -> {
                Text(
                    text = stringResource(R.string.camera_permission_permanently_denied),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                PermissionButton(
                    text = stringResource(R.string.permission_open_settings_button),
                    onClick = { handler.openSettings() },
                )
            }
            else -> {
                Text(
                    text = stringResource(R.string.camera_permission_rationale),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                PermissionButton(
                    text = stringResource(R.string.camera_permission_grant_button),
                    onClick = { handler.launchRequest() },
                )
            }
        }
    }
}

@Composable
private fun LocationPermissionSection(
    onGranted: () -> Unit = {},
    onDenied: (isPermanent: Boolean) -> Unit = {},
) {
    HandlePermissionsRequest(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        onGranted = onGranted,
        onDenied = onDenied,
    ) { handler ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(R.string.location_permission_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(R.string.location_permission_handler_example),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(4.dp))

            when {
                handler.isGranted -> {
                    Text(text = stringResource(R.string.location_permission_granted))
                }
                handler.isPermanentlyDenied -> {
                    PermissionButton(
                        text = stringResource(R.string.permission_open_settings_button),
                        onClick = { handler.openSettings() },
                    )
                }
                else -> {
                    PermissionButton(
                        text = stringResource(R.string.location_permission_request_button),
                        onClick = { handler.launchRequest() },
                    )
                }
            }
        }
    }
}

@Composable
private fun PermissionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CameraPermissionContentGrantedPreview() {
    MaterialTheme {
        CameraPermissionContent(
            handler = PermissionsHandler(
                permission = Manifest.permission.CAMERA,
                isGranted = true,
                shouldShowRationale = false,
                isPermanentlyDenied = false,
                launchRequest = {},
                openSettings = {},
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CameraPermissionContentRationalePreview() {
    MaterialTheme {
        CameraPermissionContent(
            handler = PermissionsHandler(
                permission = Manifest.permission.CAMERA,
                isGranted = false,
                shouldShowRationale = true,
                isPermanentlyDenied = false,
                launchRequest = {},
                openSettings = {},
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CameraPermissionContentPermanentlyDeniedPreview() {
    MaterialTheme {
        CameraPermissionContent(
            handler = PermissionsHandler(
                permission = Manifest.permission.CAMERA,
                isGranted = false,
                shouldShowRationale = false,
                isPermanentlyDenied = true,
                launchRequest = {},
                openSettings = {},
            ),
        )
    }
}
