package com.github.aliakseikaraliou.oner.base.utils.permission

import android.Manifest.permission

enum class Permission(val value: String) {
    READ_SMS(permission.READ_SMS),
    READ_CONTACTS(permission.READ_CONTACTS),
    WRITE_CONTACTS(permission.WRITE_CONTACTS);

    companion object {
        fun byValue(value: String) = Permission
            .values()
            .first { value == it.value }
    }
}