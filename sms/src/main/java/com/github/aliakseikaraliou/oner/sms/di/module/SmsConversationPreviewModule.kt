package com.github.aliakseikaraliou.oner.sms.di.module

import android.content.ContentResolver
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.aliakseikaraliou.oner.sms.di.scope.SmsScope
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewFragment
import com.github.aliakseikaraliou.oner.sms.ui.vm.SmsConversationPreviewViewModel
import com.github.aliakseikaraliou.oner.sms.ui.vm.SmsConversationPreviewViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SmsConversationPreviewModule {

    @SmsScope
    @Provides
    fun contentResolver(context: Context): ContentResolver = context.contentResolver

    @SmsScope
    @Provides
    fun viewModel(
        fragment: SmsConversationPreviewFragment,
        provider: SmsConversationPreviewViewModelProviderFactory
    ) =
        ViewModelProvider(
            fragment,
            provider
        ).get(SmsConversationPreviewViewModel::class.java)
}