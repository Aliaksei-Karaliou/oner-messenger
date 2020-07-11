package com.github.aliakseikaraliou.oner.sms.ui

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.github.aliakseikaraliou.oner.base.models.conversation.ConversationPreview
import com.github.aliakseikaraliou.oner.base.view.ConversationPreviewFragment
import com.github.aliakseikaraliou.oner.sms.di.component.SmsConversationPreviewComponent
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel.CheckPermission
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel.DataLoaded
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewModel.RequirePermission
import com.github.aliakseikaraliou.oner.sms.ui.vm.SmsConversationPreviewViewModel
import javax.inject.Inject

class SmsConversationPreviewFragment : ConversationPreviewFragment() {
    @Inject
    lateinit var viewModel: SmsConversationPreviewViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SmsConversationPreviewComponent.inject(this)

        viewModel.liveData.observe(viewLifecycleOwner, Observer { previewModel ->
            when (previewModel) {
                is CheckPermission -> checkPermissions(previewModel.permissions)
                is RequirePermission -> requestPermissions(previewModel.permissions)
                is DataLoaded -> dataLoaded(previewModel.data)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.validatePermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val permissionMap = permissions
            .mapIndexed { index, s -> s to (grantResults.getOrNull(index) == PERMISSION_GRANTED) }
            .toMap()

        viewModel.permissionsMap(permissionMap, false)
    }

    private fun checkPermissions(permissions: List<String>) {
        context?.let { context ->
            val permissions = permissions
                .map { permission ->
                    permission to
                            (ActivityCompat.checkSelfPermission(
                                context,
                                permission
                            ) == PERMISSION_GRANTED)
                }.toMap()
            viewModel.permissionsMap(permissions, true)
        }

    }

    private fun requestPermissions(permissions: List<String>) {
        requestPermissions(permissions.toTypedArray(), CONTACT_SMS_CODE)
    }

    private fun dataLoaded(data: List<ConversationPreview>) {
        TODO("Not yet implemented")
    }

    companion object {
        const val CONTACT_SMS_CODE = 200
    }

}