package com.github.aliakseikaraliou.oner.sms.provider

import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.models.conversation.SmsConversationPreview
import com.github.aliakseikaraliou.oner.sms.repository.SmsMessageRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SmsMessageProvider @Inject constructor(val smsMessageRepository: SmsMessageRepository) {
    fun loadSms(account: SmsAccount, loadContacts: Boolean) =
        Single.create<List<SmsConversationPreview>> { emitter ->
            GlobalScope.launch {
                try {
                    emitter.onSuccess(
                        smsMessageRepository.loadConversationPreviews(
                            account,
                            loadContacts
                        )
                    )
                } catch (t: Throwable) {
                    emitter.onError(t)
                }
            }
        }
}