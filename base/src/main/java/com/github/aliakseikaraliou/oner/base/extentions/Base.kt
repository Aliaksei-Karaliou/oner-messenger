package com.github.aliakseikaraliou.oner.base.extentions

import java.io.Closeable

fun <T : Closeable, R> T.useReturning(block: (T) -> R): R? {
    var item: R? = null

    use {
        item = block(this)
    }

    return item
}