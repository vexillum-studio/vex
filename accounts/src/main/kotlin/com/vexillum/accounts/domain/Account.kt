package com.vexillum.accounts.domain

import com.vexillum.util.AccountId
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Table(name = "account")
@Entity
data class Account(

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: AccountId = 0,

    @Column(nullable = false)
    @NotBlank
    val email: String,

    @Column(nullable = false)
    @NotBlank
    val name: String,

    @Column(nullable = false)
    @NotBlank
    val phone: String,

    @Column(nullable = false)
    @NotBlank
    val birthDate: LocalDate,

)
