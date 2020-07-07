package com.github.aliakseikaraliou.oner.sms.models.conversation

import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview
import com.github.aliakseikaraliou.oner.base.models.message.Message

data class SmsConversationPreview(override val message: Message) :
    ConversationPreview