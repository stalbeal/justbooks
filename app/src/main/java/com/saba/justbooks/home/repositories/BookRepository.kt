package com.saba.justbooks.com.saba.justbooks.home.repositories

import com.saba.justbooks.com.saba.justbooks.home.models.Book

interface BookRepository {

    suspend fun getBooksByCategories(query: String) : List<Book>

}

