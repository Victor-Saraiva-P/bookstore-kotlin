package com.kotlinwebapp.bookstore.services.impl

import com.kotlinwebapp.bookstore.domain.BookSummary
import com.kotlinwebapp.bookstore.domain.entities.BookEntity
import com.kotlinwebapp.bookstore.repositories.AuthorRepository
import com.kotlinwebapp.bookstore.repositories.BookRepository
import com.kotlinwebapp.bookstore.services.BookService
import com.kotlinwebapp.bookstore.toBookEntity
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository
) : BookService {


}