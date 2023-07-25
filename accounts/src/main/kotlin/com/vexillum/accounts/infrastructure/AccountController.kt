package com.vexillum.accounts.infrastructure

import com.vexillum.accounts.application.AccountService
import com.vexillum.accounts.domain.Account
import com.vexillum.services.TagService
import com.vexillum.util.AuthenticationMiddleware
import com.vexillum.util.handleRequest
import com.vexillum.web.accounts.AccountRegisterDTO
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService,
    private val tagService: TagService
) : AuthenticationMiddleware() {

    @PostMapping("/")
    fun registerAccount(
        @RequestBody
        @Valid
        dto: AccountRegisterDTO
    ) =
        handleRequest {
            accountService.registerAccount(dto)
        }

    @PutMapping("/")
    fun updateAccount(
        @RequestBody
        @Valid
        updatedAccount: Account
    ) =
        accountService.updateAccount(updatedAccount)

    @GetMapping("/{id}")
    fun getAccountById(
        @PathVariable("id")
        id: Long
    ) =
        handleRequest {
            accountService.getAccountById(id)
        }

    @DeleteMapping("/{id}")
    fun deleteAccountById(
        @PathVariable("id")
        id: Long
    ) =
        handleRequest {
            accountService.removeById(id)
        }

    @GetMapping("/")
    fun listAllAccounts() =
        handleRequest {
            accountService.getAll()
        }
}