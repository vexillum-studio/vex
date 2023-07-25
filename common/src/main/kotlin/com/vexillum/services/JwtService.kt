package com.vexillum.services

import com.vexillum.extensions.toLocalDateTime
import com.vexillum.util.AccountId
import com.vexillum.util.apiError
import com.vexillum.web.auth.Authentication
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import io.jsonwebtoken.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
@Service
class JwtService(
    @Value("\${vexillum.security.jwt.secret}")
    private val secret: String,
    @Value("\${vexillum.security.jwt.expiration}")
    private val expiration: Duration
) {

    fun generateToken(
        id: AccountId,
        claims: MutableMap<String, Any> = mutableMapOf()
    ): String =
        Instant.now().let { now ->
            Jwts.builder()
                .setClaims(claims)
                .setSubject(id.toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expiration)))
                .signWith(HS512, secret)
                .compact()
        }

    fun authenticate(token: String): Authentication =
        try {
            val claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
            with(claims) {
                Authentication(
                    id = subject.toLong(),
                    createdAt = issuedAt.toLocalDateTime(),
                    validUntil = expiration.toLocalDateTime()
                )
            }
        } catch (e: SignatureException) {
            apiError("Invalid JWT signature", FORBIDDEN)
        } catch (e: ExpiredJwtException) {
            apiError("Expired JWT", FORBIDDEN)
        } catch (e: Exception) {
            apiError("Invalid JWT", FORBIDDEN)
        }

}