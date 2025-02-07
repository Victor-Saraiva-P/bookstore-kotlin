package com.kotlinwebapp.bookstore.controllers

import com.kotlinwebapp.bookstore.domain.dto.BookSummaryDto
import com.kotlinwebapp.bookstore.exceptions.InvalidAuthorException
import com.kotlinwebapp.bookstore.services.AuthorService
import com.kotlinwebapp.bookstore.services.BookService
import com.kotlinwebapp.bookstore.toBookSummary
import com.kotlinwebapp.bookstore.toBookSummaryDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping(path = ["/v1/books"])
class BooksController(
    private val bookService: BookService
) {

}