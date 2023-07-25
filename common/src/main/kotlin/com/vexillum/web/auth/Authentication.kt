package com.vexillum.web.auth

import com.vexillum.util.AccountId
import java.time.LocalDateTime

data class Authentication(
    val id: AccountId,
    val createdAt: LocalDateTime,
    val validUntil: LocalDateTime
)