package com.github.aliakseikaraliou.oner.base.view

import android.content.pm.PackageManager.PERMISSION_DENIED
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import com.github.aliakseikaraliou.oner.base.utils.permission.Permission
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionObserver
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionResult
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionStatus.DENIED
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionStatus.GRANTED
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionStatus.NONE

abstract class BaseFragment : Fragment() {
    val permissionObserver = PermissionObserver.create(this)

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val permissionResult = permissions
            .mapIndexed { index, permission ->
                Permission.byValue(permission) to when (grantResults.getOrNull(index)) {
                    PERMISSION_GRANTED -> GRANTED
                    PERMISSION_DENIED -> DENIED
                    else -> NONE
                }
            }
            .toMap()

        permissionObserver.notify(requestCode, PermissionResult(permissionResult))
    }
}