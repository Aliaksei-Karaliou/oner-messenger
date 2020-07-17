package com.github.aliakseikaraliou.oner.sms.models.message

import com.github.aliakseikaraliou.oner.base.models.contact.Contact
import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus
import com.github.aliakseikaraliou.oner.base.models.message.OutgoingMessage
import java.util.*

data class SmsOutgoingMessage(
    override val id: Long,
    override val contact: Contact,
    override val address: String,
    override val text: String,
    override val date: Date,
    override val status: MessageStatus,
    override val threadId: Int
) : SmsMessage, OutgoingMessage

