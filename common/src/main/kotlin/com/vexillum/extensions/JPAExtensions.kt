package com.vexillum.extensions

import org.springframework.data.jpa.repository.JpaRepository

fun <T, ID> JpaRepository<T, ID>.searchById(id: ID): T? =
    findById(id).orElse(null)

fun <T, ID> JpaRepository<T, ID>.removeById(id: ID): T? =
    searchById(id)?.let {
        delete(it)
        it
    }
