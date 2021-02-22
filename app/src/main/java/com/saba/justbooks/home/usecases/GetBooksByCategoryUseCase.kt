package com.saba.justbooks.home.usecases

import com.saba.core.models.Book
import com.saba.justbooks.home.repositories.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetBooksByCategoryUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    fun execute(categoryName: String): Flow<Collection<Book>> = flow {
        emit(bookRepository.getBooksByCategories("subject: $categoryName"))
    }.map { booksByCategory ->
        val bookSet = HashSet<Book>()
        booksByCategory.map { books ->
            bookSet.addAll(listOf(books))
        }
        bookSet
    }

}