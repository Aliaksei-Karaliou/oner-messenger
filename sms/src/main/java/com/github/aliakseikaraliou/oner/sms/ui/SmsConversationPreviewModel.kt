package com.github.aliakseikaraliou.oner.sms.ui

import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview

sealed class SmsConversationPreviewModel {
    class DataLoaded(val data: List<ConversationPreview>) : SmsConversationPreviewModel()
    class Failed(val t: Throwable) : SmsConversationPreviewModel()
}