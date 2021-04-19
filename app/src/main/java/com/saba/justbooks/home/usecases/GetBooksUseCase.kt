package com.saba.justbooks.home.usecases

import com.saba.core.usecases.category.GetSelectedCategoriesUseCase
import com.saba.core.usecases.category.model.Category
import com.saba.core.models.Book
import com.saba.justbooks.home.repositories.BookRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository,
    private val getSelectedCategoriesUseCase: GetSelectedCategoriesUseCase
) {

    fun execute(): Flow<Collection<Book>> =
        getSelectedCategoriesUseCase.execute().map{categories->

                bookRepository.getBooksByCategories(makeQuery(getRandomCategory(categories)))
            }

    private fun getRandomCategory(categories: Collection<Category>): String {
        return categories.random().id
    }

    private fun makeQuery(category: String): String {
        return "subject: $category"
    }

}

