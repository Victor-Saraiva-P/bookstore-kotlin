package com.kotlinwebapp.bookstore

import com.kotlinwebapp.bookstore.domain.dto.AuthorDto

fun testAuthorDtoA(id: Long? = null) = AuthorDto(
    id = id,
    name = "nome de teste",
    age = 404,
    description = "descricao",
    image = "author-image.jpeg",
)

