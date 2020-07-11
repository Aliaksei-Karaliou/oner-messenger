package com.github.aliakseikaraliou.oner.sms.models.message

import com.github.aliakseikaraliou.oner.base.models.contact.Contact
import com.github.aliakseikaraliou.oner.base.models.message.IncomingMessage
import java.util.*

data class SmsIncomingMessage(
    override val id: Long,
    override val contact: Contact,
    override val text: String,
    override val date: Date,
    override val isRead: Boolean,
    override val threadId: Int
) : SmsMessage, IncomingMessage

