package com.kotlinwebapp.bookstore.services.impl

import com.kotlinwebapp.bookstore.repositories.AuthorRepository
import com.kotlinwebapp.bookstore.testAuthorEntityA
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class AuthorServiceImplTest @Autowired constructor(
    private val underTest: AuthorServiceImpl,
    private val authorRepository: AuthorRepository
) {

    @Test
    fun `test que salva e persiste o author na database`() {
        val savedData = underTest.save(testAuthorEntityA())
        assertThat(savedData.id).isNotNull()

        val recalledAuthor = authorRepository.findByIdOrNull(savedData.id)
        assertThat(recalledAuthor).isNotNull()
        assertThat(recalledAuthor!!).isEqualTo(
            testAuthorEntityA(
                id = savedData.id,
            )
        )
    }

    @Test
    fun `test que list retorna uma lista vazia quando nao houver authors`() {
        val result = underTest.list()
        assertThat(result).isEmpty()
    }

    @Test
    fun `test que list retorna authors quando tiver authors na database`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        val expected = listOf(savedAuthor)
        val result = underTest.list()
        assertThat(result).isEqualTo(expected)
    }
}