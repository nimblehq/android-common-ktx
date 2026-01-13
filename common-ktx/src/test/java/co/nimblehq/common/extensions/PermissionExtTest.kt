package co.nimblehq.common.extensions

import android.content.Intent
import android.provider.Settings
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PermissionExtTest {

    @Test
    fun `when creating PermissionsHandler, all properties are correctly initialized`() {
        val permission = "android.permission.CAMERA"
        val isGranted = true
        val shouldShowRationale = false
        val isPermanentlyDenied = false
        val launchRequest: () -> Unit = {}
        val openSettings: (android.content.Context) -> Intent = { Intent() }
	    
        val handler = PermissionsHandler(
            permission = permission,
            isGranted = isGranted,
            shouldShowRationale = shouldShowRationale,
            isPermanentlyDenied = isPermanentlyDenied,
            launchRequest = launchRequest,
            openSettings = openSettings
        )
	    
        assertThat(handler.permission, `is`(permission))
        assertThat(handler.isGranted, `is`(isGranted))
        assertThat(handler.shouldShowRationale, `is`(shouldShowRationale))
        assertThat(handler.isPermanentlyDenied, `is`(isPermanentlyDenied))
    }

    @Test
    fun `when copying PermissionsHandler, it creates new instance with modified properties`() {
	    val permission = "android.permission.CAMERA"
        val original = PermissionsHandler(
            permission = permission,
            isGranted = false,
            shouldShowRationale = false,
            isPermanentlyDenied = false,
            launchRequest = {},
            openSettings = { Intent() }
        )
	    
        val modified = original.copy(isGranted = true)
	    
        assertThat(modified.isGranted, `is`(true))
        assertThat(modified.permission, `is`(original.permission))
        assertThat(original.isGranted, `is`(false)) // Original unchanged
    }

    @Test
    fun `when comparing PermissionsHandler instances with same properties, they are equal`() {
	    val permission = "android.permission.CAMERA"
        val launchRequest: () -> Unit = {}
        val openSettings: (android.content.Context) -> Intent = { Intent() }

        val handler1 = PermissionsHandler(
            permission = permission,
            isGranted = true,
            shouldShowRationale = false,
            isPermanentlyDenied = false,
            launchRequest = launchRequest,
            openSettings = openSettings
        )

        val handler2 = PermissionsHandler(
            permission = permission,
            isGranted = true,
            shouldShowRationale = false,
            isPermanentlyDenied = false,
            launchRequest = launchRequest,
            openSettings = openSettings
        )
	    
        assertThat(handler1, `is`(handler2))
    }

    @Test
    fun `when comparing PermissionsHandler instances with different permissions, they are not equal`() {
        val launchRequest: () -> Unit = {}
        val openSettings: (android.content.Context) -> Intent = { Intent() }

        val handler1 = PermissionsHandler(
            permission = "android.permission.CAMERA",
            isGranted = true,
            shouldShowRationale = false,
            isPermanentlyDenied = false,
            launchRequest = launchRequest,
            openSettings = openSettings
        )

        val handler2 = PermissionsHandler(
            permission = "android.permission.LOCATION",
            isGranted = true,
            shouldShowRationale = false,
            isPermanentlyDenied = false,
            launchRequest = launchRequest,
            openSettings = openSettings
        )
	    
        assertThat(handler1 == handler2, `is`(false))
    }

    @Test
    fun `when destructuring PermissionsHandler, all properties are correctly extracted`() {
		val expectedPermission = "android.permission.CAMERA"
	    
        val handler = PermissionsHandler(
            permission = expectedPermission,
            isGranted = true,
            shouldShowRationale = false,
            isPermanentlyDenied = false,
            launchRequest = {},
            openSettings = { Intent() }
        )
	    
        val (permission, isGranted, shouldShowRationale, isPermanentlyDenied, _, _) = handler
	    
        assertThat(permission, `is`(expectedPermission))
        assertThat(isGranted, `is`(true))
        assertThat(shouldShowRationale, `is`(false))
        assertThat(isPermanentlyDenied, `is`(false))
    }
}
