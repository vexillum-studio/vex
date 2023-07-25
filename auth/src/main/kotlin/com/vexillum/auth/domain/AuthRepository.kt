package com.vexillum.auth.domain

import com.vexillum.util.AccountId
import org.springframework.data.jpa.repository.JpaRepository

interface AuthRepository: JpaRepository<Auth, AccountId>