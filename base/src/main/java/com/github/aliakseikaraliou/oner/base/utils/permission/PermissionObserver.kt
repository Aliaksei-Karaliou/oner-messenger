package com.github.aliakseikaraliou.oner.base.utils.permission

import androidx.fragment.app.Fragment

interface PermissionObserver {
    fun checkPermission(permission: Permission): PermissionStatus

    fun checkPermissions(vararg permissions: Permission): PermissionResult

    fun requirePermissions(
        permissions: List<Permission>,
        callback: (PermissionResult) -> Unit
    )

    fun notify(requestCode: Int, permissions: PermissionResult)

    companion object {
        fun create(fragment: Fragment): PermissionObserver = FragmentPermissionObserver(fragment)
    }
}