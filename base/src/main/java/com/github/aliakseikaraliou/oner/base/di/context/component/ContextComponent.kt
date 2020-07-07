package com.github.aliakseikaraliou.oner.base.di.context.component

import android.content.Context
import com.github.aliakseikaraliou.oner.base.di.context.module.ContextModule
import com.github.aliakseikaraliou.oner.base.di.context.provider.ContextProvider
import com.github.aliakseikaraliou.oner.base.di.exceptions.DaggerComponentNotInitializedException
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [ContextModule::class]
)
@Singleton
interface ContextComponent : ContextProvider {

    companion object {
        private var component: ContextComponent? = null

        fun inject(context: Context) {
            component = DaggerContextComponent.builder()
                .contextModule(ContextModule(context))
                .build()
        }

        fun get() = component ?: throw DaggerComponentNotInitializedException("ContextComponent")
    }
}