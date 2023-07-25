package com.vexillum.accounts.application

import com.vexillum.accounts.domain.Account
import com.vexillum.accounts.domain.AccountRepository
import com.vexillum.extensions.removeById
import com.vexillum.extensions.searchById
import com.vexillum.repositories.TagRepository
import com.vexillum.web.accounts.AccountRegisterDTO
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val tagRepository: TagRepository
) : AccountMiddleware(accountRepository) {

    fun registerAccount(dto: AccountRegisterDTO): Account =
        accountRepository.save(
            with(dto) {
                Account(
                    email = email,
                    name = name,
                    phone = phone,
                    birthDate = birthDate
                )
            }
        )

    fun updateAccount(updatedAccount: Account) =
        handleAuthenticatedAccount { account ->
            with(updatedAccount) {
                accountRepository.save(
                    account.copy(
                        email = email,
                        name = name,
                        phone = phone,
                        birthDate = birthDate
                    )
                )
            }
        }

    fun getAccountById(id: Long): Account =
        accountRepository.searchById(id) ?: userNotFound(id)

    fun getAll(): List<Account> =
        accountRepository.findAll()


    @Transactional
    fun removeById(id: Long): Account =
        accountRepository.removeById(id) ?: userNotFound(id)
}