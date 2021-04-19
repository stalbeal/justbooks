package com.saba.justbooks

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.saba.core.base.AbstractViewModelCompat
import com.saba.core.base.CoroutineContextProvider
import com.saba.core.base.Reducer
import com.saba.core.usecases.category.GetCategoriesUseCase
import com.saba.justbooks.home.mvi.BooksHomeResult
import com.saba.justbooks.home.mvi.BooksHomeViewState
import com.saba.justbooks.home.mvi.BooksHomeWish
import com.saba.justbooks.home.usecases.GetBooksByCategoryUseCase
import com.saba.justbooks.home.usecases.GetBooksUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*

class BooksHomeViewModel @AssistedInject constructor(
    @Assisted private val savedState: SavedStateHandle,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBooksByCategoryUseCase: GetBooksByCategoryUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    override val reducer: Reducer<@JvmSuppressWildcards BooksHomeResult, @JvmSuppressWildcards BooksHomeViewState>,
    override val coroutineContextProvider: CoroutineContextProvider
) : AbstractViewModelCompat<BooksHomeWish, BooksHomeResult, BooksHomeViewState>(BooksHomeViewState.init()) {

    init {
        state.onEach {
            savedState.set("state", it)
        }.launchIn(viewModelScope)
    }

    override suspend fun getResult(wishBooks: BooksHomeWish): Flow<BooksHomeResult> {
        return when (wishBooks) {
            is BooksHomeWish.GetBooks -> getBooks()
            is BooksHomeWish.GetCategories -> getCategories()
            is BooksHomeWish.GetBooksByCategory -> getBooksByCategory(wishBooks.category)
        }
    }

    private fun getBooksByCategory(category: String): Flow<BooksHomeResult> =
        getBooksByCategoryUseCase.execute(category).map {
            BooksHomeResult.BooksObtained(it)
        }.flowOn(coroutineContextProvider.backgroundDispatcher)

    private fun getCategories(): Flow<BooksHomeResult> {
        val categories = (savedState.get("state") as? BooksHomeViewState)?.categories
        return if (categories != null) {
            flowOf(BooksHomeResult.FoundCachedCategories(categories))
        } else {
            flow {
                emit(
                    BooksHomeResult.CategoriesObtained(
                        getCategoriesUseCase.execute()
                    )
                )
            }.flowOn(coroutineContextProvider.backgroundDispatcher)
        }
    }

    private fun getBooks(): Flow<BooksHomeResult> {
        val books = (savedState.get("state") as? BooksHomeViewState)?.books
        return if (books != null) {
            flowOf(BooksHomeResult.FoundCachedBooks(books))
        } else {
            getBooksUseCase.execute().map {
                BooksHomeResult.BooksObtained(it)
            }.flowOn(coroutineContextProvider.backgroundDispatcher)
        }

    }

    @AssistedFactory
    interface Factory {
        fun create(savedState: SavedStateHandle): BooksHomeViewModel
    }
}


