package com.kotlinwebapp.bookstore

import com.kotlinwebapp.bookstore.domain.AuthorSummary
import com.kotlinwebapp.bookstore.domain.AuthorUpdateRequest
import com.kotlinwebapp.bookstore.domain.BookSummary
import com.kotlinwebapp.bookstore.domain.dto.AuthorDto
import com.kotlinwebapp.bookstore.domain.dto.AuthorSummaryDto
import com.kotlinwebapp.bookstore.domain.dto.AuthorUpdateRequestDto
import com.kotlinwebapp.bookstore.domain.dto.BookSummaryDto
import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity
import com.kotlinwebapp.bookstore.domain.entities.BookEntity

const val BOOK_A_ISBN = "978-1-234567-92-3"

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id,
    name = "nome de teste A",
    age = 404,
    description = "descricao do A",
    image = "author-imageA.jpeg",
)

fun testAuthorEntityA(id: Long? = null) = AuthorEntity(
    id = id,
    name = "nome de teste A",
    age = 404,
    description = "descricao do A",
    image = "author-imageA.jpeg",
)

fun testAuthorEntityB(id: Long? = null) = AuthorEntity(
    id = id,
    name = "nome de teste B",
    age = 404,
    description = "descricao do B",
    image = "author-imageB.jpeg",
)

fun testAuthorSummaryDtoA(id: Long) = AuthorSummaryDto(
    id = id,
    name = "nome de teste A",
    image = "author-imageA.jpeg",
)

fun testAuthorSummaryA(id: Long) = AuthorSummary(
    id = id,
    name = "nome de teste A",
    image = "author-imageA.jpeg",
)

fun testAuthorUpdateRequestDtoA(id: Long? = null) = AuthorUpdateRequestDto(
    id = id,
    name = "nome de teste A",
    age = 404,
    description = "descricao do A",
    image = "author-imageA.jpeg",
)

fun testAuthorUpdateRequestA(id: Long? = null) = AuthorUpdateRequest(
    id = id,
    name = "nome de teste A",
    age = 404,
    description = "descricao do A",
    image = "author-imageA.jpeg",
)

fun testBookEntityA(isbn: String, author: AuthorEntity) = BookEntity(
    isbn = isbn,
    title = "Titulo do livro A",
    description = "Descrição do livro A",
    image = "book-ImageA.jpg",
    authorEntity = author
)

fun testBookSummaryDtoA(isbn: String, author: AuthorSummaryDto) = BookSummaryDto(
    isbn = isbn,
    title = "Titulo do livro A",
    description = "Descrição do livro A",
    image = "book-ImageA.jpg",
    author = author
)

fun testBookSummaryA(isbn: String, author: AuthorSummary) = BookSummary(
    isbn = isbn,
    title = "Titulo do livro A",
    description = "Descrição do livro A",
    image = "book-ImageA.jpg",
    author = author
)

fun testBookSummaryB(isbn: String, author: AuthorSummary) = BookSummary(
    isbn = isbn,
    title = "Titulo do livro B",
    description = "Descrição do livro B",
    image = "book-ImageB.jpg",
    author = author
)
