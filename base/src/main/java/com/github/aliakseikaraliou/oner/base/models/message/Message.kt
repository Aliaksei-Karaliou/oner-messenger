package com.github.aliakseikaraliou.oner.base.models.message

import java.util.*

interface Message {
    val id: String
    val text: String
    val date: Date
    val isRead: Boolean
}