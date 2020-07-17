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

    @Synchronized
    override fun requirePermissions(
        permissions: List<Permission>,
        callback: (result: PermissionResult) -> Unit
    ) {

        val randomizer = { (0..REQUEST_CODE_LIMIT).random() }

        var requestCode = randomizer()
        while (requests.containsKey(requestCode)) {
            requestCode = randomizer()
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

    companion object {
        private const val REQUEST_CODE_LIMIT = Short.MAX_VALUE
    }
}