package com.github.aliakseikaraliou.oner.base.utils.permission

import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.aliakseikaraliou.oner.base.exceptions.ContextNotInitializedException

internal class FragmentPermissionObserver(private val fragment: Fragment) : PermissionObserver {
    private val requests = mutableMapOf<Int, (result: PermissionResult) -> Unit>()

    override fun checkPermission(permission: Permission) = fragment
        .context
        ?.let { context ->
            if (ContextCompat.checkSelfPermission(context, permission.value) == PackageManager.PERMISSION_GRANTED) {
                PermissionStatus.GRANTED
            } else {
                PermissionStatus.DENIED
            }
        } ?: throw ContextNotInitializedException()

    override fun checkPermissions(vararg permissions: Permission) = PermissionResult(permissions
        .map { it to checkPermission(it) }
        .toMap()
        .toMutableMap())

    override fun requirePermissions(
        permissions: List<Permission>,
        callback: (result: PermissionResult) -> Unit
    ) {

        var requestCode = (0..Int.MAX_VALUE).random()
        while (requests.containsKey(requestCode)) {
            requestCode = (0..Int.MAX_VALUE).random()
        }

        requests[requestCode] = callback
        fragment.requestPermissions(permissions.map { it.value }.toTypedArray(), requestCode)
    }

    @Synchronized
    override fun notify(requestCode: Int, permissions: PermissionResult) {
        requests[requestCode]?.let { callback ->
            requests.remove(requestCode)
            callback.invoke(permissions)
        }
    }
}