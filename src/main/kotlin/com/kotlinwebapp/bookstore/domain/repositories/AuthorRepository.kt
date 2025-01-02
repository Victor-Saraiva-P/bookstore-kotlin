package com.kotlinwebapp.bookstore.domain.repositories

import com.kotlinwebapp.bookstore.domain.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
}