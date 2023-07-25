package com.vexillum.web.accounts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import javax.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class AccountRegisterDTO(

    @NotBlank
    val email: String,
    val name: String,
    val phone: String,
    val birthDate: LocalDate
)
