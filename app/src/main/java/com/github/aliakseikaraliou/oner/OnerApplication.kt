package com.github.aliakseikaraliou.oner

import android.app.Application
import com.github.aliakseikaraliou.oner.base.di.context.component.ContextComponent

class OnerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ContextComponent.inject(this)
    }
}