package com.github.aliakseikaraliou.oner.base.extentions

import android.database.Cursor
import java.io.Closeable

class CursorWrapper(private val cursor: Cursor) : Closeable {
    private val columnMap = cursor
        .columnNames
        .map { it to cursor.getColumnIndex(it) }
        .toMap()

    val count
        get() = cursor.count

    fun moveToFirst() = cursor.moveToFirst()

    fun moveToNext() = cursor.moveToNext()

    fun getString(name: String): String {
        return cursor.getString(getColumnIndex(name))
    }

    fun getStringOrNull(name: String): String? {
        return cursor.getString(getColumnIndex(name))
    }

    fun getInt(name: String) = cursor.getInt(getColumnIndex(name))

    fun getBoolean(name: String): Boolean {
        val value = getString(name)

        return "TRUE".equals(value, ignoreCase = true) || value.toIntOrDefault(0) > 0
    }

    fun getLong(name: String) = cursor.getLong(getColumnIndex(name))

    fun getDouble(name: String) = cursor.getDouble(getColumnIndex(name))

    fun getByType(name: String): Any? {
        val columnIndex = getColumnIndex(name)

        return when (cursor.getType(columnIndex)) {
            Cursor.FIELD_TYPE_STRING -> cursor.getString(columnIndex)
            Cursor.FIELD_TYPE_INTEGER -> cursor.getInt(columnIndex)
            Cursor.FIELD_TYPE_FLOAT -> cursor.getDouble(columnIndex)
            Cursor.FIELD_TYPE_NULL -> null
            Cursor.FIELD_TYPE_BLOB -> throw IllegalArgumentException("Impossible to work with BLOB data type")
            else -> throw IllegalArgumentException("Unknown type ${cursor.getType(columnIndex)} for cursor")
        }
    }

    inline operator fun <reified T> get(name: String) = when (T::class) {
        String::class -> getString(name)
        Int::class -> getInt(name)
        Boolean::class -> getBoolean(name)
        Long::class -> getLong(name)
        Double::class -> getDouble(name)
        Any::class -> getByType(name)
        else -> throw IllegalArgumentException("Unknown type ${T::class.simpleName} for cursor")
    } as T

    fun toMap() = cursor
        .columnNames
        .map { it to getByType(it) }
        .toMap()

    fun toMaps(): List<Map<String, Any?>> {
        val maps = mutableListOf<Map<String, Any?>>()

        while (moveToNext()) {
            maps.add(toMap())
        }

        return maps
    }

    fun getColumnIndex(name: String): Int {
        return columnMap[name] ?: throw IllegalArgumentException("The column $name doesn't exist")
    }

    override fun close() {
        cursor.close()
    }
}

fun Cursor.toWrapper() = CursorWrapper(this)
