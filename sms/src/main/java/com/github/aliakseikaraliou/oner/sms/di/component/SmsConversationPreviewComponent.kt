package com.github.aliakseikaraliou.oner.sms.di.component

import com.github.aliakseikaraliou.oner.base.di.context.component.ContextComponent
import com.github.aliakseikaraliou.oner.sms.di.module.SmsConversationPreviewModule
import com.github.aliakseikaraliou.oner.sms.di.scope.SmsConversationPreviewScope
import com.github.aliakseikaraliou.oner.sms.ui.SmsConversationPreviewFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [ContextComponent::class],
    modules = [SmsConversationPreviewModule::class]
)
@SmsConversationPreviewScope
interface SmsConversationPreviewComponent {

    fun inject(fragment: SmsConversationPreviewFragment)

    @Component.Builder
    interface Builder {

        fun context(contextComponent: ContextComponent): Builder

        @BindsInstance
        fun fragment(fragment: SmsConversationPreviewFragment): Builder

        fun build(): SmsConversationPreviewComponent
    }

    companion object {
        fun inject(fragment: SmsConversationPreviewFragment) = DaggerSmsConversationPreviewComponent
            .builder()
            .context(ContextComponent.get())
            .fragment(fragment)
            .build()
            .inject(fragment)
    }
}