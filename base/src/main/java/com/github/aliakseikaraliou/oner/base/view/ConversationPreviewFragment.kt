package com.github.aliakseikaraliou.oner.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.aliakseikaraliou.oner.base.R

abstract class ConversationPreviewFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.convpreview_fragment, container, false)
    }

}