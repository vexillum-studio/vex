package com.vexillum.auth.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vexillum.util.AccountId
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "auth")
@Entity
data class Auth(
    @Id
    val id: AccountId = 0,
    @Column(nullable = false)
    @JsonIgnore
    val password: String
)

