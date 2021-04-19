package com.saba.justbooks.home.mvi

import com.saba.core.adapter.ViewCategory
import com.saba.core.base.Reducer
import com.saba.core.models.ViewBook
import javax.inject.Inject

class BooksHomeReducer @Inject constructor() : Reducer<BooksHomeResult, BooksHomeViewState> {
    override suspend fun reduce(stateBooks: BooksHomeViewState, from: BooksHomeResult): BooksHomeViewState {
        return when (from) {
            is BooksHomeResult.OnLoading -> stateBooks.copy(
                isIdling = false,
                isLoading = true,
                categories = null,
                books = null,
                error = null
            )
            is BooksHomeResult.FoundCachedCategories-> stateBooks.copy(
                isIdling = false,
                isLoading = false,
                categories = from.categories,
                error = null
            )
            is BooksHomeResult.CategoriesObtained -> stateBooks.copy(
                isIdling = false,
                isLoading = false,
                categories = from.categories.map {
                    ViewCategory(it)
                },
                error = null
            )
            is BooksHomeResult.FoundCachedBooks -> stateBooks.copy(
                isIdling = false,
                isLoading = false,
                books = from.books,
                error = null
            )
            is BooksHomeResult.BooksObtained -> stateBooks.copy(
                isIdling = false,
                isLoading = false,
                books = from.books.map {
                    ViewBook(it)
                },
                error = null
            )
            is BooksHomeResult.Error -> stateBooks.copy(
                isIdling = false,
                isLoading = false,
                error = from.error
            )
            is BooksHomeResult.BooksByCategoriesObtained -> stateBooks.copy(
                isIdling = false,
                isLoading = false,
                books = from.books.map {
                    ViewBook(it)
                },
                error = null
            )
        }
    }

}