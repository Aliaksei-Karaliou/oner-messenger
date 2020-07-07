package com.github.aliakseikaraliou.oner.base.repository

import com.github.aliakseikaraliou.oner.base.models.Account
import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview

interface MessageRepository<T : Account> {
    suspend fun loadConversationPreviews(account: T): List<out ConversationPreview>
}