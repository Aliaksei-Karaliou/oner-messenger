package com.github.aliakseikaraliou.oner.sms.models.message

import com.github.aliakseikaraliou.oner.base.models.message.IncomingMessage
import java.util.*

data class SmsIncomingMessage(
    override val id: String,
    override val text: String,
    override val date: Date,
    override val isRead: Boolean
) : SmsMessage, IncomingMessage

