package com.saba.bookdetail.repositories

import com.saba.core.models.Book

interface BookDetailRepository {

    suspend fun getBookByIsbn(isbn: String) : Book
}

