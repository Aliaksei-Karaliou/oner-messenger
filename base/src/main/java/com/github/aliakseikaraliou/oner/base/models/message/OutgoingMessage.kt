package com.github.aliakseikaraliou.oner.base.models.message

import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus.READ

interface OutgoingMessage : Message {
    val status: MessageStatus

    override val isRead: Boolean
        get() = status == READ
}