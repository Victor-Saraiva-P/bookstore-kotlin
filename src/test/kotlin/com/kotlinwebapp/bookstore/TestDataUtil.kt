package com.kotlinwebapp.bookstore

import com.kotlinwebapp.bookstore.domain.AuthorUpdateRequest
import com.kotlinwebapp.bookstore.domain.dto.AuthorDto
import com.kotlinwebapp.bookstore.domain.dto.AuthorUpdateRequestDto
import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity

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

