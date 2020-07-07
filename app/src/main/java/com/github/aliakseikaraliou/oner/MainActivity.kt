package com.github.aliakseikaraliou.oner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SmsConversationPreviewFragment())
                .commit()
        }
    }
}