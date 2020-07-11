package com.github.aliakseikaraliou.oner.base.models.message

enum class MessageStatus {
    DRAFT,
    FAILED,
    OUTBOX,
    SENT,
    READ,
    QUEUED
}