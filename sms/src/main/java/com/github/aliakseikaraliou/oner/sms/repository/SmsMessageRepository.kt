package com.github.aliakseikaraliou.oner.sms.repository

import android.content.ContentResolver
import android.net.Uri
import com.github.aliakseikaraliou.oner.base.Constants.ADDRESS
import com.github.aliakseikaraliou.oner.base.Constants.BODY
import com.github.aliakseikaraliou.oner.base.Constants.DATE
import com.github.aliakseikaraliou.oner.base.Constants.TYPE
import com.github.aliakseikaraliou.oner.base.Constants._ID
import com.github.aliakseikaraliou.oner.base.repository.MessageRepository
import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.models.conversation.SmsConversationPreview
import javax.inject.Inject

class SmsMessageRepository @Inject constructor(val contentResolver: ContentResolver) :
    MessageRepository<SmsAccount> {

    private val contactRepository = ContactRepository(contentResolver)

    override suspend fun loadConversationPreviews(account: SmsAccount): List<SmsConversationPreview> {
        val uri = Uri.parse("content://contacts/people")
        val params = arrayOf(_ID, ADDRESS, DATE, BODY, TYPE)

        val messageMap = mutableMapOf<Long, SmsConversationPreview>()

        contactRepository.loadAll()

        contentResolver
            .query(uri, null, null, null, null)
            ?.use { cursor ->
                val paramMap = cursor.columnNames
                    .map { it to cursor.getColumnIndex(it) }
                    .toMap()

                while (cursor.moveToNext()) {
                    var toMap = cursor.columnNames
                        .map { it to cursor.getString(cursor.getColumnIndex(it)) }
                        .toMap()
//                    val id = cursor.getLong(paramMap.getValue(_ID))
//                    val address = cursor.getString(paramMap.getValue(ADDRESS))
//                    val date = Date(cursor.getLong(paramMap.getValue(DATE)))
//                    val body = cursor.getString(paramMap.getValue(BODY))
//                    when (cursor.getInt(paramMap.getValue(TYPE))) {
//                        Telephony.TextBasedSmsColumns.MESSAGE_TYPE_INBOX -> parseIncoming()
//                        Telephony.TextBasedSmsColumns.MESSAGE_TYPE_SENT -> parseOutgoing()
//                    }
                    val t = 0
                }
            }

        return listOf()
    }

    private fun parseIncoming() {
    }

    private fun parseOutgoing() {
    }
}