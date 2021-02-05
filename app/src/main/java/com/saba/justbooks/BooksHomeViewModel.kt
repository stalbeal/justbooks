package com.saba.justbooks

import com.saba.core.base.AbstractViewModel
import com.saba.core.base.CoroutineContextProvider
import com.saba.core.base.Reducer
import com.saba.core.usecases.category.GetCategoriesUseCase
import com.saba.justbooks.com.saba.justbooks.home.mvi.BooksHomeResult
import com.saba.justbooks.com.saba.justbooks.home.mvi.BooksHomeViewState
import com.saba.justbooks.com.saba.justbooks.home.mvi.BooksHomeWish
import com.saba.justbooks.com.saba.justbooks.home.usecases.GetBooksUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BooksHomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    override val reducer: Reducer<@JvmSuppressWildcards BooksHomeResult, @JvmSuppressWildcards BooksHomeViewState>,
    override val coroutineContextProvider: CoroutineContextProvider
) : AbstractViewModel<BooksHomeWish, BooksHomeResult, BooksHomeViewState>(BooksHomeViewState.init()) {

    override suspend fun getResult(wishBooks: BooksHomeWish): Flow<BooksHomeResult> {
        return when (wishBooks) {
            is BooksHomeWish.GetBooks -> getBooks()
            is BooksHomeWish.GetCategories -> getCategories()
            is BooksHomeWish.GetBooksByCategory -> getBooksByCategory(wishBooks.category)
        }
    }

    private fun getBooksByCategory(category: String): Flow<BooksHomeResult> =
        getBooksUseCase.execute().map {
            BooksHomeResult.BooksObtained(it)
        }.flowOn(coroutineContextProvider.backgroundDispatcher)

    private fun getCategories(): Flow<BooksHomeResult> =
        flow {
            emit(
                BooksHomeResult.CategoriesObtained(
                    getCategoriesUseCase.execute()
                )
            )
        }

    private fun getBooks(): Flow<BooksHomeResult> =
        getBooksUseCase.execute().map {
            BooksHomeResult.BooksObtained(it)
        }.flowOn(coroutineContextProvider.backgroundDispatcher)

}