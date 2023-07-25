package com.vexillum.accounts.domain

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>



