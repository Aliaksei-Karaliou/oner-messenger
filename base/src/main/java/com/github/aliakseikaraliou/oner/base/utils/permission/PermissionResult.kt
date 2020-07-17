package com.github.aliakseikaraliou.oner.base.utils.permission

import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionStatus.GRANTED
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionStatus.NONE

class PermissionResult(private var permissionMap: Map<Permission, PermissionStatus>) {

    operator fun get(permission: Permission) = permissionMap[permission] ?: NONE

    fun isGranted(permission: Permission) = permissionMap[permission] == GRANTED

    fun isAllGranted() = permissionMap.values.all { it == GRANTED }

    fun failed() = permissionMap
        .filter { it.value != GRANTED }
        .keys
        .toList()

    fun combineWith(permissionResult: PermissionResult) {
        val permissionMap = mutableMapOf<Permission, PermissionStatus>()
        permissionMap.putAll(this.permissionMap)
        permissionMap.putAll(permissionResult.permissionMap)

        this.permissionMap = permissionMap
    }
}