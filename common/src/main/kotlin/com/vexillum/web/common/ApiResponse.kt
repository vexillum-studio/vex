package com.vexillum.web.common

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

@JsonInclude(NON_NULL)
data class ApiResponse(
    val httpStatus: HttpStatus,
    val status: Int = httpStatus.value(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val data: Any? = null,
    val error: String? = null
) {
    constructor(
        httpStatus: HttpStatus = OK,
        data: Any? = null,
        error: String? = null
    ) : this(
        httpStatus = httpStatus,
        status = httpStatus.value(),
        timestamp = LocalDateTime.now(),
        data = data,
        error = error
    )

    fun asResponseEntity(): ResponseEntity<ApiResponse> =
        ResponseEntity.status(httpStatus).body(this)
}