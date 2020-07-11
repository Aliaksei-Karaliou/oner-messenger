package com.github.aliakseikaraliou.oner.sms.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.github.aliakseikaraliou.oner.base.view.ConversationPreviewFragment
import com.github.aliakseikaraliou.oner.sms.di.component.SmsConversationPreviewComponent
import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.repository.SmsMessageRepository
import com.github.aliakseikaraliou.oner.sms.ui.vm.SmsConversationPreviewViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SmsConversationPreviewFragment : ConversationPreviewFragment() {
    @Inject
    lateinit var viewModel: SmsConversationPreviewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SmsConversationPreviewComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SmsConversationPreviewViewModel::class.java)

        GlobalScope.launch {
            activity?.let {
                var loadAll =
                    SmsMessageRepository(it.contentResolver).loadConversationPreviews(SmsAccount())
                loadAll
            }

        }
    }

}