package com.github.aliakseikaraliou.oner.sms.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.aliakseikaraliou.oner.base.view.ConversationPreviewFragment

class SmsConversationPreviewFragment : ConversationPreviewFragment() {

    companion object {
        fun newInstance() = SmsConversationPreviewFragment()
    }

    private lateinit var viewModel: SmsConversationPreviewViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SmsConversationPreviewViewModel::class.java)
    }

}