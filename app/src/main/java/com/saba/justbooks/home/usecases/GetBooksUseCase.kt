package com.saba.justbooks.com.saba.justbooks.home.usecases

import com.saba.core.base.CoroutineContextProvider
import com.saba.core.usecases.category.GetSelectedCategoriesUseCase
import com.saba.core.usecases.category.model.Category
import com.saba.justbooks.com.saba.justbooks.home.models.Book
import com.saba.justbooks.com.saba.justbooks.home.repositories.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val getSelectedCategoriesUseCase: GetSelectedCategoriesUseCase,
    private val coroutineContextProvider: CoroutineContextProvider
) {

    fun execute(): Flow<Collection<Book>> = getSelectedCategoriesUseCase.execute().map {
        it.map { category ->
            flow {
                emit(bookRepository.getBooksByCategories("subject: ${category.id}"))
            }
        }
    }.flatMapLatest {
        combine(it) { books ->
            books.toList()
        }.map { booksByCategory ->
            val bookSet = HashSet<Book>()
            booksByCategory.map { books ->
                bookSet.addAll(books)
            }
            bookSet
        }
    }


    private fun getSelectedCategories(): Flow<Collection<Category>> {
        return getSelectedCategoriesUseCase.execute()
            .flowOn(coroutineContextProvider.backgroundDispatcher)
    }

    private fun createCategoriesQuery(categories: Collection<Category>): String {
        return categories.joinToString("", "subject:", "+", -1)
    }

}

