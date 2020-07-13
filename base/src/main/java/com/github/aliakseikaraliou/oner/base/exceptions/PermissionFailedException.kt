package com.github.aliakseikaraliou.oner.base.exceptions

import com.github.aliakseikaraliou.oner.base.utils.permission.Permission

class PermissionFailedException(vararg permissions: Permission) : SecurityException("Permissions ${permissions.joinToString(separator = ", ")} failed")