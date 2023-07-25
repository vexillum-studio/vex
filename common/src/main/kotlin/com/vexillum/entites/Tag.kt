package com.vexillum.entites

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tag")
data class Tag(

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String
) {
    override fun equals(other: Any?): Boolean =
        other is Tag && other.id == id

}