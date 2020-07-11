package com.github.aliakseikaraliou.oner.sms.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.aliakseikaraliou.oner.sms.provider.SmsMessageProvider
import javax.inject.Inject

class SmsConversationPreviewViewModelProviderFactory @Inject constructor(val provider: SmsMessageProvider) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        SmsConversationPreviewViewModel::class.java -> SmsConversationPreviewViewModel(provider)
        else -> throw IllegalArgumentException("SmsConversationPreviewViewModelProviderFactory cannot create instance of ${modelClass.name}")
    } as T
}