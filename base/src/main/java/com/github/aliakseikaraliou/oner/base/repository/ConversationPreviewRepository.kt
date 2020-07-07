package com.github.aliakseikaraliou.oner.base.repository

import com.github.aliakseikaraliou.oner.base.models.Account
import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview

interface ConversationPreviewRepository<T : Account> {
    fun loadAll(account: T): List<out ConversationPreview>
}