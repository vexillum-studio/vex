package com.vexillum.web.auth

import com.vexillum.util.AccountId
import com.vexillum.util.Password

data class AuthLoginDTO(
    val id: AccountId,
    val password: Password
)