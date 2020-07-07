package com.github.aliakseikaraliou.oner.sms.models.contact

import com.github.aliakseikaraliou.oner.base.models.contact.User

data class SmsUser(
    override val id: String,
    override val displayName: String,
    val phoneNumbers: List<String>
) : SmsContact, User