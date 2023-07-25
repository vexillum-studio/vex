package com.vexillum.accounts.application

import com.vexillum.accounts.domain.Account
import com.vexillum.accounts.domain.AccountRepository
import com.vexillum.util.AuthenticationMiddleware
import com.vexillum.util.apiError
import com.vexillum.web.common.ApiResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.ResponseEntity

open class AccountMiddleware(
    private val accountRepository: AccountRepository
) : AuthenticationMiddleware() {

    fun <T> handleAuthenticatedAccount(block: (account: Account) -> T?): ResponseEntity<ApiResponse> =
        handleAuthenticatedRequest { auth ->
            val account = accountRepository.findByIdOrNull(auth.id)?: userNotFound(auth.id)
            block(account)
        }

    protected fun userNotFound(id: Long): Nothing =
        apiError("The account with id $id doesn't exist", NOT_FOUND)

}
