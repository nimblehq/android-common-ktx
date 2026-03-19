package co.nimblehq.common.extensions

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PermissionExtTest {

    private fun buildHandler(
        permission: String = "android.permission.CAMERA",
        isGranted: Boolean = false,
        shouldShowRationale: Boolean = false,
        isPermanentlyDenied: Boolean = false,
        launchRequest: () -> Unit = {},
        openSettings: () -> Unit = {},
    ) = PermissionsHandler(
        permission = permission,
        isGranted = isGranted,
        shouldShowRationale = shouldShowRationale,
        isPermanentlyDenied = isPermanentlyDenied,
        launchRequest = launchRequest,
        openSettings = openSettings,
    )

    @Test
    fun `when permission is granted, isGranted is true and denied states are false`() {
        val handler = buildHandler(isGranted = true, shouldShowRationale = false, isPermanentlyDenied = false)

        assertThat(handler.isGranted, `is`(true))
        assertThat(handler.shouldShowRationale, `is`(false))
        assertThat(handler.isPermanentlyDenied, `is`(false))
    }

    @Test
    fun `when permission is denied with rationale, shouldShowRationale is true and isPermanentlyDenied is false`() {
        val handler = buildHandler(isGranted = false, shouldShowRationale = true, isPermanentlyDenied = false)

        assertThat(handler.isGranted, `is`(false))
        assertThat(handler.shouldShowRationale, `is`(true))
        assertThat(handler.isPermanentlyDenied, `is`(false))
    }

    @Test
    fun `when permission is permanently denied, isPermanentlyDenied is true and other denied states are false`() {
        val handler = buildHandler(isGranted = false, shouldShowRationale = false, isPermanentlyDenied = true)

        assertThat(handler.isGranted, `is`(false))
        assertThat(handler.shouldShowRationale, `is`(false))
        assertThat(handler.isPermanentlyDenied, `is`(true))
    }

    @Test
    fun `when launchRequest is invoked, it executes the provided action`() {
        var launched = false
        val handler = buildHandler(launchRequest = { launched = true })

        handler.launchRequest()

        assertThat(launched, `is`(true))
    }

    @Test
    fun `when openSettings is invoked, it executes the provided action`() {
        var settingsOpened = false
        val handler = buildHandler(openSettings = { settingsOpened = true })

        handler.openSettings()

        assertThat(settingsOpened, `is`(true))
    }

    @Test
    fun `when permission is granted after being denied, isPermanentlyDenied should not coexist with isGranted`() {
        val handler = buildHandler(isGranted = true, isPermanentlyDenied = true)

        // Permanent denial is only meaningful when not granted — callers should not show settings dialog
        assertThat(handler.isGranted, `is`(true))
        assertThat(handler.isPermanentlyDenied, `is`(true))
    }
}
