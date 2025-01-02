package com.kotlinwebapp.bookstore.domain.repositories

import com.kotlinwebapp.bookstore.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
}