package com.vexillum.util

import com.vexillum.web.common.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
fun <T> handleRequest(block: () -> T?): ResponseEntity<ApiResponse> {
    val response = try {
        ApiResponse(data = block())
    } catch (e: Exception) {
        println(e.message)
        when (e) {
            is ApiException -> ApiResponse(httpStatus = e.status, error = e.message)
            else -> ApiResponse(httpStatus = INTERNAL_SERVER_ERROR, error = e.message)
        }
    }
    return response.asResponseEntity()
}

class ApiException(
    override val message: String,
    val status: HttpStatus = BAD_REQUEST
) : Exception(message)

fun apiError(
    message: String,
    status: HttpStatus = BAD_REQUEST
): Nothing = throw ApiException(message, status)
