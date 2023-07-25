package com.vexillum.util

import com.vexillum.services.JwtService
import com.vexillum.web.auth.Authentication
import com.vexillum.web.common.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

open class AuthenticationMiddleware {

    @Autowired
    private lateinit var jwtService: JwtService

    fun <T> handleAuthenticatedRequest(
        block: (auth: Authentication) -> T?
    ): ResponseEntity<ApiResponse> =
        handleRequest {
            val requestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
            val request = requestAttributes.request
            val authHeader = request.getHeader(AUTH_HEADER)
            val jwtToken = authHeader.removePrefix(BEARER_PREFIX)
            block(jwtService.authenticate(jwtToken))
        }

    companion object {
        private const val AUTH_HEADER = "Authorization"
        private const val BEARER_PREFIX = "Bearer "
    }
}