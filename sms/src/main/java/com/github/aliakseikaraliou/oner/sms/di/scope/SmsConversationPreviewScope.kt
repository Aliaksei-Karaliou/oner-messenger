package com.github.aliakseikaraliou.oner.sms.di.scope

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Scope
@Retention(RUNTIME)
@Target(CLASS, FUNCTION)
annotation class SmsConversationPreviewScope