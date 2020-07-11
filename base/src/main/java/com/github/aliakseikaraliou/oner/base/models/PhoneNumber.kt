package com.github.aliakseikaraliou.oner.base.models

data class PhoneNumber private constructor(val value: String) {
    companion object {
        fun create(number: String): PhoneNumber {
            val re = Regex("[\\D]")

            return PhoneNumber(re.replace(number, ""))
        }
    }
}