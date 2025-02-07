package com.kotlinwebapp.bookstore

import com.kotlinwebapp.bookstore.domain.*
import com.kotlinwebapp.bookstore.domain.dto.*
import com.kotlinwebapp.bookstore.domain.entities.*
import com.kotlinwebapp.bookstore.exceptions.InvalidAuthorException

/*
 * ======================
 *     Extensões para Autores
 * ======================
 */

// Extensões para AuthorEntity
fun AuthorEntity.toAuthorDto(): AuthorDto =
    AuthorDto(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        age = this.age
    )

fun AuthorEntity.toAuthorSummaryDto(): AuthorSummaryDto {
    val authorId = this.id ?: throw InvalidAuthorException()
    return AuthorSummaryDto(
        id = authorId,
        name = this.name,
        image = this.image
    )
}

// Extensões para AuthorDto
fun AuthorDto.toAuthorEntity(): AuthorEntity =
    AuthorEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        age = this.age
    )

// Extensões para AuthorUpdateRequestDto
fun AuthorUpdateRequestDto.toAuthorUpdateRequest(): AuthorUpdateRequest =
    AuthorUpdateRequest(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        age = this.age
    )

// Extensões para AuthorSummaryDto
fun AuthorSummaryDto.toAuthorSummary(): AuthorSummary =
    AuthorSummary(
        id = this.id,
        name = this.name,
        image = this.image
    )

/*
 * ======================
 *     Extensões para Livros
 * ======================
 */

// Extensão para BookSummary: converte para BookEntity
fun BookSummary.toBookEntity(author: AuthorEntity): BookEntity =
    BookEntity(
        isbn = this.isbn,
        title = this.title,
        description = this.description,
        image = this.image,
        authorEntity = author
    )

// Extensão para BookSummaryDto: converte para BookSummary
fun BookSummaryDto.toBookSummary(): BookSummary =
    BookSummary(
        isbn = this.isbn,
        title = this.title,
        description = this.description,
        image = this.image,
        author = this.author.toAuthorSummary()
    )

// Extensão para BookEntity: converte para BookSummaryDto
fun BookEntity.toBookSummaryDto(): BookSummaryDto =
    BookSummaryDto(
        isbn = this.isbn,
        title = this.title,
        description = this.description,
        image = this.image,
        author = this.authorEntity.toAuthorSummaryDto()
    )
