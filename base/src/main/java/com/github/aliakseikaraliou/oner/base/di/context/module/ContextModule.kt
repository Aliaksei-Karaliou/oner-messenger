package com.github.aliakseikaraliou.oner.base.di.context.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(val context: Context) {
    @Provides
    @Singleton
    fun context() = context
}