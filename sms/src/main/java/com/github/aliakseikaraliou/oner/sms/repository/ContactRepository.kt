package com.github.aliakseikaraliou.oner.sms.repository

import android.content.ContentResolver
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone.CONTACT_ID
import android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI
import android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER
import android.provider.ContactsContract.Contacts.DISPLAY_NAME
import android.provider.ContactsContract.Contacts.HAS_PHONE_NUMBER
import android.provider.ContactsContract.Contacts.PHOTO_URI
import android.provider.ContactsContract.Contacts._ID
import com.github.aliakseikaraliou.oner.base.extentions.toWrapper
import com.github.aliakseikaraliou.oner.base.extentions.useReturning
import com.github.aliakseikaraliou.oner.base.models.PhoneNumber
import com.github.aliakseikaraliou.oner.sms.models.contact.SmsUser
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contentResolver: ContentResolver) {
    fun loadAll(): List<SmsUser> {
        val uri = ContactsContract.Contacts.CONTENT_URI
        val users = mutableListOf<SmsUser>()

        contentResolver
            .query(uri, null, null, null, null)
            ?.toWrapper()
            ?.use { cursor ->
                mutableListOf<Map<String, String>>()

                while (cursor.moveToNext()) {
                    if (cursor.getBoolean(HAS_PHONE_NUMBER)) {
                        users.add(loadById(cursor.getLong(_ID)))
                    }

                }
            }
        return users.sortedBy { it.fullName }
    }

    fun loadById(id: Long): SmsUser {
        val phoneNumbers = mutableListOf<PhoneNumber>()

        val params = arrayOf(
            NUMBER, DISPLAY_NAME, CONTACT_ID, PHOTO_URI
        )

        var displayName: String? = null
        var photoUrl: String? = null

        contentResolver
            .query(
                CONTENT_URI,
                params,
                "$CONTACT_ID = ?",
                arrayOf(id.toString()),
                null
            )
            ?.toWrapper()
            ?.use { cursor ->
                while (cursor.moveToNext()) {
                    displayName = cursor[DISPLAY_NAME]
                    photoUrl = cursor.getStringOrNull(PHOTO_URI)
                    phoneNumbers.add(PhoneNumber.create(cursor[NUMBER]))
                }
            }

        return SmsUser(
            id = id,
            fullName = displayName,
            phoneNumbers = phoneNumbers,
            photoUrl = photoUrl
        )
    }

    fun loadByAddress(phoneNumber: String): SmsUser? {
        val params = arrayOf(
            CONTACT_ID
        )

        return contentResolver
            .query(CONTENT_URI, params, "$NUMBER = ?", arrayOf(phoneNumber), null)
            ?.toWrapper()
            ?.takeIf { it.moveToFirst() }
            ?.useReturning { it.getLong(CONTACT_ID) }
            ?.let { id -> loadById(id) }
    }
}