package com.kotlinwebapp.bookstore.services.impl

import com.kotlinwebapp.bookstore.domain.entities.AuthorEntity
import com.kotlinwebapp.bookstore.repositories.AuthorRepository
import com.kotlinwebapp.bookstore.services.AuthorService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service                 /*          Dependecy Injection              */
class AuthorServiceImpl( private val authorRepository: AuthorRepository) : AuthorService {

    override fun create(authorEntity: AuthorEntity): AuthorEntity {
        return authorRepository.save(authorEntity)
    }

    override fun list(): List<AuthorEntity> {
        return authorRepository.findAll()
    }

    override fun get(id: Long): AuthorEntity? {
        return authorRepository.findByIdOrNull(id)
    }

}