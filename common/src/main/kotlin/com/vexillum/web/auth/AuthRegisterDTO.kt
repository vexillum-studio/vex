package com.vexillum.web.auth

import com.vexillum.util.AccountId
import com.vexillum.util.Password

data class AuthRegisterDTO(
    val id: AccountId,
    val password: Password
)