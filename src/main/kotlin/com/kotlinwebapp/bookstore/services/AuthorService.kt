package com.kotlinwebapp.bookstore.services

import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity
import java.util.*

interface AuthorService {
    fun save(authorEntity: AuthorEntity): AuthorEntity

    fun list(): List<AuthorEntity>

    fun get(id: Long): AuthorEntity?
}