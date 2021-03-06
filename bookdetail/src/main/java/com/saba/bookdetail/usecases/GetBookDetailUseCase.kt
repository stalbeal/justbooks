package com.saba.bookdetail.usecases

import com.saba.bookdetail.repositories.BookDetailRepository
import com.saba.core.models.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBookDetailUseCase @Inject constructor(private val bookDetailRepository: BookDetailRepository) {

    suspend fun execute(isbn: String): Book {
        return bookDetailRepository.getBookByIsbn(isbn)
    }

}