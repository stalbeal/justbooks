package com.saba.justbooks.home.repositories

import com.saba.core.models.Book

interface BookRepository {

    suspend fun getBooksByCategories(query: String) : List<Book>

}

