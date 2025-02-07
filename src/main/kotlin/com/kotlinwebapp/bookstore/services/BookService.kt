package com.kotlinwebapp.bookstore.services

import com.kotlinwebapp.bookstore.domain.BookSummary
import com.kotlinwebapp.bookstore.domain.dto.BookDto
import com.kotlinwebapp.bookstore.domain.dto.BookSummaryDto
import com.kotlinwebapp.bookstore.domain.entities.BookEntity

interface BookService {
    fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean>
}