package com.saba.bookdetail.repositories

import com.saba.core.models.Book
import com.saba.core.network.services.BookService
import javax.inject.Inject

class BookDetailRepositoryImpl @Inject constructor(private val service: BookService) : BookDetailRepository {


    override suspend fun getBookByIsbn(isbn: String) : Book {
        return Book(service.getBookByIsbn(isbn))
    }

}