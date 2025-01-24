package com.kotlinwebapp.bookstore.services

import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity

interface AuthorService {
    fun save(authorEntity: AuthorEntity): AuthorEntity

    fun list(): List<AuthorEntity>
}