package com.github.aliakseikaraliou.oner.sms.ui

import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview

sealed class SmsConversationPreviewModel {
    class CheckPermission(val permissions: List<String>) : SmsConversationPreviewModel()
    class RequirePermission(val permissions: List<String>) : SmsConversationPreviewModel()
    class DataLoaded(val data: List<ConversationPreview>) : SmsConversationPreviewModel()
    class DataLoadingFailed(val t: Throwable) : SmsConversationPreviewModel()
}