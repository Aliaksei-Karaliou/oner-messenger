package com.github.aliakseikaraliou.oner.base.di.exceptions

class DaggerComponentNotInitializedException(val componentName: String) :
    Exception("$componentName not initialized")