package com.github.aliakseikaraliou.oner.sms.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aliakseikaraliou.oner.base.exceptions.PermissionFailedException
import com.github.aliakseikaraliou.oner.base.utils.permission.Permission.READ_CONTACTS
import com.github.aliakseikaraliou.oner.base.utils.permission.Permission.READ_SMS
import com.github.aliakseikaraliou.oner.base.utils.permission.PermissionObserver
import com.github.aliakseikaraliou.oner.sms.models.SmsAccount
import com.github.aliakseikaraliou.oner.sms.provider.SmsMessageProvider
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel.DataLoaded
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel.Failed

class SmsConversationPreviewViewModel(
    val provider: SmsMessageProvider,
    val permissionObserver: PermissionObserver
) : ViewModel() {

    val liveData = MutableLiveData<SmsConversationPreviewModel>()

    fun loadConversationPreviews() {
        val permissions = permissionObserver.checkPermissions(READ_CONTACTS, READ_SMS)

        if (permissions.isAllGranted()) {
            loadConversationPreviewsWithGrantedPermissions(true)
        } else {
            val failed = permissions.failed()

            permissionObserver.requirePermissions(failed) { result ->
                permissions.combineWith(result)

                if (permissions.isGranted(READ_SMS)) {
                    val loadContacts = permissions.isGranted(READ_CONTACTS)
                    loadConversationPreviewsWithGrantedPermissions(loadContacts)
                } else {
                    liveData.postValue(SmsConversationPreviewModel.Failed(PermissionFailedException(READ_SMS)))
                }
            }
        }
    }

    private fun loadConversationPreviewsWithGrantedPermissions(loadContacts: Boolean) {
        provider
            .loadSms(SmsAccount(), loadContacts)
            .subscribe({ preview ->
                liveData.postValue(DataLoaded(preview))
            }, {
                liveData.postValue(Failed(it))
            })
    }
}