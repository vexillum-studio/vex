package com.vexillum.auth.application

import com.vexillum.auth.domain.Auth
import com.vexillum.auth.domain.AuthRepository
import com.vexillum.web.auth.AuthLoginDTO
import com.vexillum.web.auth.AuthRegisterDTO
import com.vexillum.services.JwtService
import com.vexillum.util.apiError
import com.vexillum.web.auth.AuthTokenDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JwtService,
    private val authRepository: AuthRepository
) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerAuth(dto: AuthRegisterDTO): Auth {
        if(authRepository.findByIdOrNull(dto.id) != null) {
            apiError("Account with id ${dto.id} already registered")
        }
        val auth = Auth(
            id = dto.id,
            password = passwordEncoder.encode(dto.password)
        )
        return authRepository.save(auth)
    }

    fun loginAuth(dto: AuthLoginDTO): AuthTokenDTO {
        val (id, password) = dto
        val auth = authRepository.findByIdOrNull(id)
            ?: apiError("The auth with id: $id was no found", NOT_FOUND)
        if (!passwordEncoder.matches(password, auth.password)) {
            apiError("Invalid user or password", FORBIDDEN)
        }
        return AuthTokenDTO(jwtService.generateToken(id))
    }

}
