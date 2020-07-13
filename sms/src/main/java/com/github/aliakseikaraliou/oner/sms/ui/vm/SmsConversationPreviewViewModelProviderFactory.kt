package com.github.aliakseikaraliou.oner.sms.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionObserver
import com.github.aliakseikaraliou.oner.sms.provider.SmsMessageProvider
import javax.inject.Inject

class SmsConversationPreviewViewModelProviderFactory @Inject constructor(
    private val provider: SmsMessageProvider,
    private val permissionObserver: PermissionObserver
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = when (modelClass) {
        SmsConversationPreviewViewModel::class.java -> SmsConversationPreviewViewModel(provider, permissionObserver)
        else -> throw IllegalArgumentException("SmsConversationPreviewViewModelProviderFactory cannot create instance of ${modelClass.name}")
    } as T
}