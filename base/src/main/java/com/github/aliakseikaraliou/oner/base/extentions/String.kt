package com.github.aliakseikaraliou.oner.base.extentions

fun String.toIntOrDefault(default: Int) = toIntOrNull() ?: default