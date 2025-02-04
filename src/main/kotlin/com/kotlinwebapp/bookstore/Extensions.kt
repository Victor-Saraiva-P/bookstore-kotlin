package com.kotlinwebapp.bookstore

import com.kotlinwebapp.bookstore.domain.AuthorUpdateRequest
import com.kotlinwebapp.bookstore.domain.dto.AuthorDto
import com.kotlinwebapp.bookstore.domain.dto.AuthorUpdateRequestDto
import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity

fun AuthorEntity.toAuthorDto(): AuthorDto {
    return AuthorDto(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        age = this.age,
    )
}

fun AuthorDto.toAuthorEntity(): AuthorEntity {
    return AuthorEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        age = this.age,
    )
}

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = this.id,
    name = this.name,
    image = this.image,
    description = this.description,
    age = this.age,
)