package com.saba.justbooks.home.repositories

import com.saba.core.network.services.BookService
import com.saba.core.models.Book
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {

    override suspend fun getBooksByCategories(query: String): List<Book> {
        return bookService.getBooksByCategories(query, "newest").items?.filter {
            it.bookInfo.industryIdentifier != null && !it.bookInfo.authors.isNullOrEmpty()
        }?.map { item ->
            Book(item)
        } ?: listOf()
    }
}

