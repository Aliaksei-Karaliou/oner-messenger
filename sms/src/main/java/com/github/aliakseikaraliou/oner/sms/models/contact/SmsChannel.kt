package com.github.aliakseikaraliou.oner.sms.models.contact

import com.github.aliakseikaraliou.oner.base.models.contact.Channel

data class SmsChannel(
    override val id: Long,
    override val displayName: String
) : Channel, SmsContact {

    override val photoUrl: String?
        get() = null
}