package com.github.aliakseikaraliou.oner.base.models.message

interface OutgoingMessage : Message {
    val isSent: Boolean
}