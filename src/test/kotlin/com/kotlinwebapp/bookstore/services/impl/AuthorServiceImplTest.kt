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
        val savedData = underTest.create(testAuthorEntityA())
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

    @Test
    fun `test que retorna null quando nenhum author estiver presente na database`() {
        val result = underTest.get(999)
        assertThat(result).isNull()
    }

    @Test
    fun `test que o get retorna o author quando tiver presente um author na database`() {
        val savedAuthor = authorRepository.save(testAuthorEntityA())
        val result = underTest.get(savedAuthor.id!!)
        assertThat(result).isNotNull()
    }
}