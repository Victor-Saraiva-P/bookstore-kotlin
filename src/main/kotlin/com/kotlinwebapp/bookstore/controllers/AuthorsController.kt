package com.kotlinwebapp.bookstore.controllers

import com.kotlinwebapp.bookstore.domain.dto.AuthorDto
import com.kotlinwebapp.bookstore.services.AuthorService
import com.kotlinwebapp.bookstore.toAuthorDto
import com.kotlinwebapp.bookstore.toAuthorEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping(path = ["/v1/authors"])
class AuthorsController(private val authorService: AuthorService) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto> {
        val createdAuthor = authorService.save(
            authorDto.toAuthorEntity()
        ).toAuthorDto()

        return ResponseEntity(createdAuthor, HttpStatus.CREATED)
    }

    @GetMapping
    fun readManyAuthors(): List<AuthorDto> {
        return authorService.list().map { it.toAuthorDto() }
    }
}