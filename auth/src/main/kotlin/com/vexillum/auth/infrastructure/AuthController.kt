package com.vexillum.auth.infrastructure

import com.vexillum.auth.application.AuthService
import com.vexillum.util.AuthenticationMiddleware
import com.vexillum.web.auth.AuthLoginDTO
import com.vexillum.web.auth.AuthRegisterDTO
import com.vexillum.util.handleRequest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) : AuthenticationMiddleware() {

    @PostMapping("/register")
    fun registerAuth(@RequestBody dto: AuthRegisterDTO) =
        handleRequest {
            authService.registerAuth(dto)
        }

    @PostMapping("/login")
    fun login(@RequestBody dto: AuthLoginDTO) =
        handleRequest {
            authService.loginAuth(dto)
        }

    @GetMapping("/secure")
    fun login() =
        handleAuthenticatedRequest { auth ->
            auth
        }

}