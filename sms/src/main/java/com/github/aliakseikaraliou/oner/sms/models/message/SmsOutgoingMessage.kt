package com.github.aliakseikaraliou.oner.sms.models.message

import com.github.aliakseikaraliou.oner.base.models.message.OutgoingMessage
import java.util.*

data class SmsOutgoingMessage(
    override val id: String,
    override val text: String,
    override val date: Date,
    override val isRead: Boolean,
    override val isSent: Boolean
) : SmsMessage, OutgoingMessage

