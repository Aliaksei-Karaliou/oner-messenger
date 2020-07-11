package com.github.aliakseikaraliou.oner.base.models.message

import com.github.aliakseikaraliou.oner.base.models.contact.Contact
import java.util.*

interface Message {
    val id: Long
    val contact: Contact
    val text: String
    val date: Date
    val isRead: Boolean
}