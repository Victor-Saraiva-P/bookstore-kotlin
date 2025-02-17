package com.kotlinwebapp.bookstore.domain.dto

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class AuthorDto(
    val id: Long?,
    val name: String,
    val age: Int,
    val description: String,
    val image: String
)