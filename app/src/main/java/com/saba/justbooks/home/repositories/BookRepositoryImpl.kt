package com.saba.justbooks.home.repositories

import com.saba.core.network.services.BookService
import com.saba.core.models.Book
import com.saba.core.models.IndustryIdentifier
import com.saba.core.network.models.APIIndustryIdentifierResponse
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {

    override suspend fun getBooksByCategories(subject: String): List<Book> {
        return bookService.getBooksByCategories(subject).items?.filter {
            isValidIndustryIdentifier(it.bookInfo.industryIdentifier) && !it.bookInfo.authors.isNullOrEmpty()
        }?.map { item ->
            Book(item)
        } ?: listOf()
    }

    private fun isValidIndustryIdentifier(identifiers: List<APIIndustryIdentifierResponse>?): Boolean {
        var isValid = false

            identifiers?.forEach{item ->
                if(item.type.toLowerCase().contains(ISBN)){
                    isValid = true
                }
            }
        return isValid
    }

    companion object {
        private const val ISBN = "isbn"
    }
}

