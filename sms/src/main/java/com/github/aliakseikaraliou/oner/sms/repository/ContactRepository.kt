package com.github.aliakseikaraliou.oner.sms.repository

import android.content.ContentResolver
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone.NUMBER
import android.provider.ContactsContract.Contacts.DISPLAY_NAME
import android.provider.ContactsContract.Contacts.HAS_PHONE_NUMBER
import android.provider.ContactsContract.Contacts._ID
import com.github.aliakseikaraliou.oner.base.extentions.toWrapper
import com.github.aliakseikaraliou.oner.sms.models.contact.SmsUser

class ContactRepository(val contentResolver: ContentResolver) {
    fun loadAll(): List<SmsUser> {
        val uri = ContactsContract.Contacts.CONTENT_URI
        val users = mutableListOf<SmsUser>()

        contentResolver
            .query(uri, null, null, null, null)
            ?.toWrapper()
            ?.use { cursor ->
                val list = mutableListOf<Map<String, String>>()

                while (cursor.moveToNext()) {
                    val toMap = cursor.toMap()

                    if (cursor.getBoolean(HAS_PHONE_NUMBER)) {
                        val id = cursor.getInt(_ID)
                        val displayName = cursor.getString(DISPLAY_NAME)
                        val smsUser = SmsUser(
                            id = id.toString(),
                            displayName = displayName,
                            phoneNumbers = loadPhoneNumbers(id)
                        )
                        users.add(smsUser)
                    }

                }
            }
        return users
    }

    private fun loadPhoneNumbers(id: Int): List<String> {
        val phoneNumbers = mutableListOf<String>()

        val params = arrayOf(
            NUMBER
        )

        contentResolver
            .query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                arrayOf(id.toString()),
                null
            )
            ?.toWrapper()
            ?.use { cursor ->
                while (cursor.moveToNext()) {
                    phoneNumbers.add(cursor.getString(NUMBER))
                }
            }

        return phoneNumbers
    }
}