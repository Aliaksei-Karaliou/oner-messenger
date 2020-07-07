package com.github.aliakseikaraliou.oner.sms.repository

import com.github.aliakseikaraliou.oner.base.repository.ConversationPreviewRepository
import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.models.conversation.SmsConversationPreview
import javax.inject.Inject

class SmsConversationPreviewRepository @Inject constructor() :
    ConversationPreviewRepository<SmsAccount> {
    override fun loadAll(account: SmsAccount): List<SmsConversationPreview> {
        TODO("Not yet implemented")
    }
}