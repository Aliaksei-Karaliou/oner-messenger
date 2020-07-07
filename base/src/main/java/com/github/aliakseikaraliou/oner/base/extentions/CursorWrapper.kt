package com.github.aliakseikaraliou.oner.base.extentions

import android.database.Cursor
import java.io.Closeable

class CursorWrapper(val cursor: Cursor) : Closeable {
    private val columnMap = cursor
        .columnNames
        .map { it to cursor.getColumnIndex(it) }
        .toMap()

    fun moveToNext() = cursor.moveToNext()

    fun getString(name: String) = cursor.getString(columnIndex(name))

    fun getInt(name: String) = cursor.getInt(columnIndex(name))

    fun getBoolean(name: String): Boolean {
        val value = getString(name)

        return "TRUE".equals(value, ignoreCase = true) || value.toIntOrDefault(0) > 0
    }

    fun toMap() = cursor.columnNames
        .map { it to getString(it) }
        .toMap()

    private fun columnIndex(name: String): Int {
        return columnMap[name] ?: throw IllegalArgumentException("The column $name doesn't exist")
    }

    override fun close() {
        cursor.close()
    }
}

fun Cursor.toWrapper() = CursorWrapper(this)
