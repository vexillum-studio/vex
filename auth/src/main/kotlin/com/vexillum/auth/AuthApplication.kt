package com.vexillum.auth
import com.vexillum.constants.Packages.COMMON
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(basePackages = [COMMON])
@EntityScan(COMMON)
@EnableJpaRepositories(COMMON)
class AuthApplication

fun main(args: Array<String>) {
    runApplication<AuthApplication>(*args)
}
