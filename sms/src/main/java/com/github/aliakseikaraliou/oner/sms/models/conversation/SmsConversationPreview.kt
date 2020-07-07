package com.github.aliakseikaraliou.oner.sms.models.conversation

import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview
import com.github.aliakseikaraliou.oner.sms.models.message.SmsMessage

data class SmsConversationPreview(override val message: SmsMessage) : ConversationPreview