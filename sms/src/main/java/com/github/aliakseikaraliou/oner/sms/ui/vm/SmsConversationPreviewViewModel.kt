package com.github.aliakseikaraliou.oner.sms.ui.vm

import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_SMS
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.provider.SmsMessageProvider
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel

class SmsConversationPreviewViewModel(val provider: SmsMessageProvider) : ViewModel() {
    val liveData = MutableLiveData<SmsConversationPreviewModel>()

    private val permissions = mutableMapOf<String, Boolean>()

    fun validatePermissions() {
        liveData.postValue(
            SmsConversationPreviewModel.CheckPermission(
                listOf(
                    READ_SMS,
                    READ_CONTACTS
                )
            )
        )
    }

    fun permissionsMap(permissions: Map<String, Boolean>, requestFailed: Boolean) {
        this.permissions.putAll(permissions)

        if (requestFailed) {
            val failedPermissions = permissions
                .filter { !it.value }
                .keys
                .toList()

            liveData.postValue(
                SmsConversationPreviewModel.RequirePermission(failedPermissions)
            )
        }

        if (permissions[READ_SMS] == true) {
            loadPreviews()
        }
    }

    private fun loadPreviews() {
        provider
            .loadSms(SmsAccount(), permissions[READ_CONTACTS] == true)
            .subscribe({
                liveData.postValue(SmsConversationPreviewModel.DataLoaded(it))
            }, {
                liveData.postValue(SmsConversationPreviewModel.DataLoadingFailed(it))
            })
    }
}