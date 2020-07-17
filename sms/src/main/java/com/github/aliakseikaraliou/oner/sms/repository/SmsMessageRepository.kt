package com.github.aliakseikaraliou.oner.sms.repository

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.BaseColumns._ID
import android.provider.Telephony
import android.provider.Telephony.TextBasedSmsColumns.ADDRESS
import android.provider.Telephony.TextBasedSmsColumns.BODY
import android.provider.Telephony.TextBasedSmsColumns.DATE
import android.provider.Telephony.TextBasedSmsColumns.MESSAGE_TYPE_DRAFT
import android.provider.Telephony.TextBasedSmsColumns.MESSAGE_TYPE_FAILED
import android.provider.Telephony.TextBasedSmsColumns.MESSAGE_TYPE_INBOX
import android.provider.Telephony.TextBasedSmsColumns.MESSAGE_TYPE_OUTBOX
import android.provider.Telephony.TextBasedSmsColumns.MESSAGE_TYPE_QUEUED
import android.provider.Telephony.TextBasedSmsColumns.MESSAGE_TYPE_SENT
import android.provider.Telephony.TextBasedSmsColumns.READ
import android.provider.Telephony.TextBasedSmsColumns.THREAD_ID
import android.provider.Telephony.TextBasedSmsColumns.TYPE
import com.github.aliakseikaraliou.oner.base.extentions.CursorWrapper
import com.github.aliakseikaraliou.oner.base.extentions.toWrapper
import com.github.aliakseikaraliou.oner.base.models.PhoneNumber
import com.github.aliakseikaraliou.oner.base.models.contact.Contact
import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus.DRAFT
import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus.FAILED
import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus.OUTBOX
import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus.QUEUED
import com.github.aliakseikaraliou.oner.base.models.message.MessageStatus.SENT
import com.github.aliakseikaraliou.oner.sms.models.contact.SmsChannel
import com.github.aliakseikaraliou.oner.sms.models.contact.SmsUser
import com.github.aliakseikaraliou.oner.sms.models.conversation.SmsConversationPreview
import com.github.aliakseikaraliou.oner.sms.models.message.SmsIncomingMessage
import com.github.aliakseikaraliou.oner.sms.models.message.SmsOutgoingMessage
import java.util.*
import javax.inject.Inject

@SuppressLint("Recycle")
class SmsMessageRepository @Inject constructor(private val contentResolver: ContentResolver) {

    private val contactRepository = ContactRepository(contentResolver)

    fun loadConversationPreviews(
        loadContacts: Boolean
    ): List<SmsConversationPreview> {
        val uri = Telephony.Sms.CONTENT_URI
        val params = arrayOf(_ID, ADDRESS, DATE, BODY, TYPE, THREAD_ID, READ)

        val messages = mutableListOf<SmsConversationPreview>()

        contentResolver
            .query(uri, params, "$THREAD_ID IS NOT NULL) GROUP BY ($THREAD_ID", null, null)
            ?.toWrapper()
            ?.use { cursor ->
                while (cursor.moveToNext()) {
                    val smsMessage = when (cursor.getInt(TYPE)) {
                        MESSAGE_TYPE_INBOX -> parseIncoming(cursor, loadContacts)
                        MESSAGE_TYPE_SENT,
                        MESSAGE_TYPE_OUTBOX,
                        MESSAGE_TYPE_FAILED,
                        MESSAGE_TYPE_QUEUED -> parseOutgoing(cursor, loadContacts)
                        else -> throw IllegalArgumentException(
                            "No sms message status defined:${cursor.getInt(
                                TYPE
                            )}"
                        )
                    }
                    val smsConversationPreview = SmsConversationPreview(smsMessage)
                    messages.add(smsConversationPreview)
                }
            }
        return messages
    }

    private fun parseIncoming(
        cursor: CursorWrapper,
        loadContacts: Boolean
    ): SmsIncomingMessage {
        val id = cursor.getLong(_ID)
        val contact = loadContact(cursor, loadContacts)
        val address = cursor.getString(ADDRESS)
        val date = Date(cursor.getLong(DATE))
        val body = cursor.getString(BODY)
        val isRead = cursor.getBoolean(READ)
        val threadId = cursor.getInt(THREAD_ID)

        return SmsIncomingMessage(id, contact, address, body, date, isRead, threadId)
    }

    private fun parseOutgoing(
        cursor: CursorWrapper,
        loadContacts: Boolean
    ): SmsOutgoingMessage {
        val id = cursor.getLong(_ID)
        val contact = loadContact(cursor, loadContacts)
        val address = cursor.getString(ADDRESS)
        val date = Date(cursor.getLong(DATE))
        val body = cursor.getString(BODY)
        val status = parseMessageStatus(cursor.getInt(TYPE))
        val threadId = cursor.getInt(THREAD_ID)

        return SmsOutgoingMessage(id, contact, address, body, date, status, threadId)
    }

    private fun loadContact(
        cursor: CursorWrapper,
        loadContacts: Boolean
    ): Contact {
        val address = cursor.getString(ADDRESS)

        val user = if (loadContacts) {
            contactRepository.loadByAddress(address)
        } else {
            SmsUser(0, null, null, listOf(PhoneNumber.create(address)))
        }

        return user ?: SmsChannel(cursor.getLong(THREAD_ID), address)
    }

    private fun parseMessageStatus(status: Int) = when (status) {
        MESSAGE_TYPE_SENT -> SENT
        MESSAGE_TYPE_OUTBOX -> OUTBOX
        MESSAGE_TYPE_FAILED -> FAILED
        MESSAGE_TYPE_QUEUED -> QUEUED
        MESSAGE_TYPE_DRAFT -> DRAFT
        else -> throw IllegalArgumentException("No sms message status defined:$status")
    }
}