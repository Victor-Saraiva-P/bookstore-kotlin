package com.kotlinwebapp.bookstore.services.impl

import com.kotlinwebapp.bookstore.*
import com.kotlinwebapp.bookstore.domain.AuthorSummary
import com.kotlinwebapp.bookstore.repositories.AuthorRepository
import com.kotlinwebapp.bookstore.repositories.BookRepository
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@Transactional
class BookServiceImplTest @Autowired constructor(
    private val underTest: BookServiceImpl,
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
) {

    @Test
    fun `test que createUpdate throw IllegalStateException quando o author nao existir`() {
        val authorSummary = AuthorSummary(id = 999L)
        val bookSummary = testBookSummaryA(BOOK_A_ISBN, authorSummary)
        assertThrows<IllegalStateException> {
            underTest.createUpdate(BOOK_A_ISBN, bookSummary)
        }
    }

    @Test
    fun `test que o createUpdate com sucesso create um book no database`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        assertThat(savedAuthor).isNotNull()

        val authorSummary = AuthorSummary(id = savedAuthor.id!!)
        val bookSummary = testBookSummaryA(BOOK_A_ISBN, authorSummary)
        val (savedBook, isCreated) = underTest.createUpdate(BOOK_A_ISBN, bookSummary)
        assertThat(savedBook).isNotNull()

        val recaledBook = bookRepository.findByIdOrNull(BOOK_A_ISBN)
        assertThat(recaledBook).isNotNull().isEqualTo(savedBook)
        assertThat(isCreated).isTrue()
    }

    @Test
    fun `test que o createUpdate com sucesso update um book no database`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        assertThat(savedAuthor).isNotNull()

        val savedBook = bookRepository.save(testBookEntityA(BOOK_A_ISBN, savedAuthor))
        assertThat(savedBook).isNotNull()

        val authorSummary = AuthorSummary(id = savedAuthor.id!!)
        val bookSummary = testBookSummaryB(BOOK_A_ISBN, authorSummary)
        val (updatedBook, isCreated) = underTest.createUpdate(BOOK_A_ISBN, bookSummary)
        assertThat(updatedBook).isNotNull()

        val recaledBook = bookRepository.findByIdOrNull(BOOK_A_ISBN)
        assertThat(recaledBook).isNotNull()
        assertThat(isCreated).isFalse()
    }

    @Test
    fun `teste que list retorna uma lista vazia quando nao houver books na database`(){
        val result = underTest.list()
        assertThat(result).isEmpty()
    }

    @Test
    fun `test que list retorna uma lista de books quando ha books na database` (){
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        assertThat(savedAuthor).isNotNull()

        val savedBook = bookRepository.save(testBookEntityA(BOOK_A_ISBN, savedAuthor))
        assertThat(savedBook).isNotNull()

        val result = underTest.list()
        assertThat(result).hasSize(1)
        assertThat(result[0]).isEqualTo(savedBook)
    }
}