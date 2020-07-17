package com.github.aliakseikaraliou.oner.base.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.aliakseikaraliou.oner.base.R
import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview
import com.github.aliakseikaraliou.oner.base.view.ConversationPreviewRecyclerViewAdapter.ViewHolder

class ConversationPreviewRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
    var previews: List<out ConversationPreview> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.convpreview_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conversationPreview = previews[position].message

        holder.textView.text = conversationPreview.text
    }

    override fun getItemCount() = previews.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById<TextView>(R.id.text)
    }
}