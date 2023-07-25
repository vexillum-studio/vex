package com.vexillum.repositories

import com.vexillum.entites.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long> {

    fun getByName(name: String): Tag?

   // @Query("SELECT t FROM Tag t WHERE t.name LIKE %:matchTarget%")
    fun findByNameContainingIgnoreCase(input: String): List<Tag>
}

