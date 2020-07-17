package com.github.aliakseikaraliou.oner.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aliakseikaraliou.oner.base.R
import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview
import kotlinx.android.synthetic.main.convpreview_fragment.*

abstract class ConversationPreviewFragment : BaseFragment() {

    private val recyclerViewAdapter = ConversationPreviewRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.convpreview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun updateData(previews: List<out ConversationPreview>) {
        recyclerViewAdapter.previews = previews
    }
}