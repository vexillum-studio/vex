package com.vexillum.accounts

import com.vexillum.constants.Packages
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(basePackages = [Packages.COMMON])
@EntityScan(Packages.COMMON)
@EnableJpaRepositories(Packages.COMMON)
class AccountsApplication
fun main(args: Array<String>) {
    runApplication<AccountsApplication>(*args)
}
