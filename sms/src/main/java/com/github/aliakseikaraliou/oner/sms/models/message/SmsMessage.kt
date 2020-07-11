package com.github.aliakseikaraliou.oner.sms.models.message

import com.github.aliakseikaraliou.oner.base.models.message.Message

interface SmsMessage : Message {
    val threadId: Int
}