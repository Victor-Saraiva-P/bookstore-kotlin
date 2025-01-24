package com.kotlinwebapp.bookstore.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity
import com.kotlinwebapp.bookstore.services.AuthorService
import com.kotlinwebapp.bookstore.testAuthorDtoA
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthorsControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockkBean val authorService: AuthorService
) {

    val objectMapper = ObjectMapper()

    @BeforeEach
    fun beforeEach() {
        every {
            authorService.save(any())
        } answers {
            firstArg()
        }
    }

    @Test
    fun `test que cria author e salva o author `() {
        mockMvc.post("/v1/authors") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }

        val expected  = AuthorEntity(
            id = null,
            name = "nome de teste",
            age = 404,
            image = "author-image.jpeg",
            description = "descricao"
        )
        verify { authorService.save(expected ) }

    }

    @Test
    fun `test que cria um author retorna um HTTP 201 status de sucesso na criacao`() {
        mockMvc.post("/v1/authors") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                AuthorEntity(
                    id = null,
                    name = "nome de teste",
                    age = 404,
                    image = "author-image.jpeg",
                    description = "descrição"
                )
            )
        }.andExpect {
            status { isCreated() }
        }
    }

}