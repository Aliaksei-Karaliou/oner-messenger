package com.github.aliakseikaraliou.oner.sms.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.aliakseikaraliou.oner.base.view.ConversationPreviewFragment
import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.repository.SmsMessageRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SmsConversationPreviewFragment : ConversationPreviewFragment() {
    private lateinit var viewModel: SmsConversationPreviewViewModel

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