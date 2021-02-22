package com.saba.justbooks.home.mvi

import com.saba.core.base.Result
import com.saba.core.usecases.category.model.Category
import com.saba.core.models.Book

sealed class BooksHomeResult : Result {

    object OnLoading : BooksHomeResult()

    data class BooksObtained(val books: Collection<Book>) : BooksHomeResult()
    data class CategoriesObtained(val categories: Collection<Category>) : BooksHomeResult()
    data class BooksByCategoriesObtained(val books: Collection<Book>) : BooksHomeResult()
    data class Error(val error: BooksHomeFailure) : BooksHomeResult()

}

