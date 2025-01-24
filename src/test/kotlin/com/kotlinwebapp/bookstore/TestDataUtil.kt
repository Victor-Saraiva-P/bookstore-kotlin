package com.kotlinwebapp.bookstore

import com.kotlinwebapp.bookstore.domain.dto.AuthorDto
import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id,
    name = "nome de teste",
    age = 404,
    description = "descricao",
    image = "author-image.jpeg",
)

fun testAuthorEntityA(id: Long? = null) = AuthorEntity(
    id = id,
    name = "nome de teste",
    age = 404,
    description = "descricao",
    image = "author-image.jpeg",
)

