package com.kotlinwebapp.bookstore.services

import com.kotlinwebapp.bookstore.domain.BookSummary
import com.kotlinwebapp.bookstore.domain.BookUpdateRequest
import com.kotlinwebapp.bookstore.domain.entities.BookEntity

interface BookService {
    fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean>

    fun list(authorId : Long? = null):List<BookEntity>

    fun get(isbn: String): BookEntity?

    fun partialUpdate(isbn: String, bookUpdateRequest: BookUpdateRequest): BookEntity
}