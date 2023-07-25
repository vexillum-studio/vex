package com.vexillum.services

import com.vexillum.entites.Tag
import com.vexillum.repositories.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository
) {

    fun findMatchesByName(input: String): List<Tag> =
        tagRepository.findByNameContainingIgnoreCase(input)
}