package com.kotlinwebapp.bookstore.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity
import com.kotlinwebapp.bookstore.services.AuthorService
import com.kotlinwebapp.bookstore.testAuthorDtoA
import com.kotlinwebapp.bookstore.testAuthorEntityA
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

private const val AUTHORS_BASE_URL = "/v1/authors"

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
            authorService.create(any())
        } answers {
            firstArg()
        }
    }

    @Test
    fun `test que cria author e salva o author `() {
        mockMvc.post(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }

        val expected = AuthorEntity(
            id = null,
            name = "nome de teste",
            age = 404,
            image = "author-image.jpeg",
            description = "descricao"
        )
        verify { authorService.create(expected) }

    }

    @Test
    fun `test que cria um author retorna um HTTP 201 status de sucesso na criacao`() {
        mockMvc.post(AUTHORS_BASE_URL) {
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

    @Test
    fun `test que cria um author e retorna 400 quando um IllegalArgument`() {
        every {
            authorService.create(any())
        } throws (IllegalArgumentException())

        mockMvc.post(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                testAuthorDtoA()
            )
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `test que list retorna uma lista vazia e HTTP 200 quando nenhum authors na database`() {
        every {
            authorService.list()
        } answers {
            emptyList()
        }

        mockMvc.get(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { json("[]") }
        }
    }

    @Test
    fun `testa que a lista retorna os authors e HTTP 200 quando tiver authors na database`() {
        every {
            authorService.list()
        } answers {
            listOf(testAuthorEntityA(1))
        }

        mockMvc.get(AUTHORS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { jsonPath("$[0].id", equalTo(1)) }
            content { jsonPath("$[0].name", equalTo("nome de teste A")) }
            content { jsonPath("$[0].age", equalTo(404)) }
            content { jsonPath("$[0].description", equalTo("descricao do A")) }
            content { jsonPath("$[0].image", equalTo("author-imageA.jpeg")) }
        }
    }

    @Test
    fun `retorna 404 quando nao for encontrado nenhum author`() {
        every {
            authorService.get(any())
        } answers {
            null
        }

        mockMvc.get("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect { status { isNotFound() } }
    }

    @Test
    fun `test que retorna 200 e um author quando um author for encontrado`() {
        every {
            authorService.get(any())
        } answers {
            testAuthorEntityA(id = 999)
        }

        mockMvc.get("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.id", equalTo(999)) }
            content { jsonPath("$.name", equalTo("nome de teste A")) }
            content { jsonPath("$.age", equalTo(404)) }
            content { jsonPath("$.description", equalTo("descricao do A")) }
            content { jsonPath("$.image", equalTo("author-imageA.jpeg")) }
        }
    }

    @Test
    fun `test o full update do author retorna 200 e o author quando tiver sucesso`() {
        every {
            authorService.fullUpdate(any(), any())
        } answers {
            secondArg()
        }

        mockMvc.put("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testAuthorDtoA(id = 999))
        }.andExpect {
            status { isOk() }
            content { jsonPath("$.id", equalTo(999)) }
            content { jsonPath("$.name", equalTo("nome de teste")) }
            content { jsonPath("$.age", equalTo(404)) }
            content { jsonPath("$.description", equalTo("descricao")) }
            content { jsonPath("$.image", equalTo("author-image.jpeg")) }
        }
    }

    @Test
    fun `test que o full updtade retorna 400 quando tem IllegalStateException`() {
        every {
            authorService.fullUpdate(any(), any())
        } throws (IllegalStateException())

        mockMvc.put("$AUTHORS_BASE_URL/999") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(testAuthorDtoA(id = 999))
        }.andExpect { status { isBadRequest() } }
    }
}