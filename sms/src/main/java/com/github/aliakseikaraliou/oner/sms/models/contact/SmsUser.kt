package com.github.aliakseikaraliou.oner.sms.models.contact

import com.github.aliakseikaraliou.oner.base.models.PhoneNumber
import com.github.aliakseikaraliou.oner.base.models.contact.User

data class SmsUser(
    override val id: Long,
    val fullName: String?,
    val phoneNumbers: List<PhoneNumber>
) : SmsContact, User {

    override val displayName: String
        get() = if (!fullName.isNullOrBlank()) {
            fullName
        } else if (phoneNumbers.isNotEmpty()) {
            phoneNumbers[0].value
        } else {
            ""
        }
}