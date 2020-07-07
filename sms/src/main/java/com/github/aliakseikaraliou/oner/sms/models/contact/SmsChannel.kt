package com.github.aliakseikaraliou.oner.sms.models.contact

import com.github.aliakseikaraliou.oner.base.models.contact.Channel

data class SmsChannel(
    override val id: String,
    override val displayName: String
) : Channel, SmsContact