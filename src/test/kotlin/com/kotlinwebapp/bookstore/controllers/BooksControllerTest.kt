package com.kotlinwebapp.bookstore.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlinwebapp.bookstore.domain.dto.AuthorSummaryDto
import com.kotlinwebapp.bookstore.domain.dto.BookSummaryDto
import com.kotlinwebapp.bookstore.services.BookService
import com.kotlinwebapp.bookstore.testAuthorEntityA
import com.kotlinwebapp.bookstore.testAuthorSummaryDtoA
import com.kotlinwebapp.bookstore.testBookEntityA
import com.kotlinwebapp.bookstore.testBookSummaryDtoA
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.hamcrest.CoreMatchers.equalTo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.result.StatusResultMatchersDsl
import java.lang.AssertionError
import kotlin.test.Test

private const val BOOKS_BASE_URL = "/v1/books"

@SpringBootTest
@AutoConfigureMockMvc
class BooksControllerTest @Autowired constructor(
    private val mockMvc: MockMvc, @MockkBean val bookService: BookService
) {
    val objectMapper = ObjectMapper()

    @Test
    fun `test que usa o createFullUpdateBook retorna 201 quando o livro eh criado`() {
        assertThatUserCreatedUpdated(true) { isCreated() }
    }

    @Test
    fun `test que usa o createFullUpdateBook retorna 200 quando o livro eh atualizado`() {
        assertThatUserCreatedUpdated(false) { isOk() }
    }

    private fun assertThatUserCreatedUpdated(
        isCreated: Boolean, statusCodeAssertionError: StatusResultMatchersDsl.() -> Unit
    ) {
        val isbn = "978-1-234567-92-3"
        val author = testAuthorEntityA(id = 1)
        val savedBook = testBookEntityA(isbn, author)

        val authorSummaryDto = testAuthorSummaryDtoA(id = 1)
        val bookSummaryDto = testBookSummaryDtoA(isbn, authorSummaryDto)

        every {
            bookService.createUpdate(isbn, any())
        } answers {
            Pair(savedBook, isCreated)
        }

        mockMvc.put("$BOOKS_BASE_URL/$isbn") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bookSummaryDto)
        }.andExpect { status { statusCodeAssertionError() } }
    }

    @Test
    fun `test que usa o createFullUpdate e retorna HTTP 500 quando o author nao esta na database`() {
        val isbn = "978-1-234567-92-3"
        val author = testAuthorEntityA()
        val savedBook = testBookEntityA(isbn, author)

        val authorSummaryDto = testAuthorSummaryDtoA(id = 1)
        val bookSummaryDto = testBookSummaryDtoA(isbn, authorSummaryDto)

        every {
            bookService.createUpdate(isbn, any())
        } answers {
            Pair(savedBook, true)
        }

        mockMvc.put("$BOOKS_BASE_URL/$isbn") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bookSummaryDto)
        }.andExpect { status { isInternalServerError() } }
    }

    @Test
    fun `test qeu eusa o createFullUpdateBook retorna HTTP 400 quando o author nao existir`() {
        val isbn = "978-1-234567-92-3"

        val authorSummaryDto = testAuthorSummaryDtoA(id = 1)
        val bookSummaryDto = testBookSummaryDtoA(isbn, authorSummaryDto)

        every {
            bookService.createUpdate(isbn, any())
        } throws IllegalStateException()

        mockMvc.put("$BOOKS_BASE_URL/$isbn") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bookSummaryDto)
        }.andExpect { status { isBadRequest() } }
    }

    @Test
    fun `test que readManyBooks retorna uma lista de books`() {
        val isbn = "978-032-539299-2658"
        val bookList = listOf(testBookEntityA(isbn = isbn, testAuthorEntityA(id = 1)))

        every {
            bookService.list()
        } answers {
            bookList
        }

        mockMvc.get(BOOKS_BASE_URL) {
            contentType = MediaType.APPLICATION_JSON
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk() }
            content { jsonPath("$[0].isbn", equalTo(isbn)) }
            content { jsonPath("$[0].title", equalTo(bookList.first().title)) }
            content { jsonPath("$[0].image", equalTo(bookList.first().image)) }
            content { jsonPath("$[0].author.name", equalTo(bookList.first().authorEntity.name)) }
            content { jsonPath("$[0].author.image", equalTo(bookList.first().authorEntity.image)) }
        }
    }

    @org.junit.jupiter.api.Test
    fun `test que lista retorna nenhum book quando nao tem nenhum book com um determinado author`() {
        every {
            bookService.list(authorId = any())
        } answers {
            emptyList()
        }

        mockMvc.get("$BOOKS_BASE_URL?author=999") {
            contentType = MediaType.APPLICATION_JSON
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk() }
            content { json("[]") }
        }
    }

    @Test
    fun `test que list so retorna books que tenham o author requisitado`() {
        val isbn = "978-032-539299-2658"
        val bookList = listOf(testBookEntityA(isbn = isbn, testAuthorEntityA(id = 1)))
        every {
            bookService.list(authorId = 1L)
        } answers {
            bookList
        }

        mockMvc.get("$BOOKS_BASE_URL?author=1") {
            contentType = MediaType.APPLICATION_JSON
            accept(MediaType.APPLICATION_JSON)
        }.andExpect {
            status { isOk() }
            content { jsonPath("$[0].isbn", equalTo(isbn)) }
            content { jsonPath("$[0].title", equalTo(bookList.first().title)) }
            content { jsonPath("$[0].image", equalTo(bookList.first().image)) }
            content { jsonPath("$[0].author.name", equalTo(bookList.first().authorEntity.name)) }
            content { jsonPath("$[0].author.image", equalTo(bookList.first().authorEntity.image)) }
        }
    }
}
