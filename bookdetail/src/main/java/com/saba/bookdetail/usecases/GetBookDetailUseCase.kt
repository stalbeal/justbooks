package com.saba.bookdetail.usecases

import com.saba.bookdetail.repositories.BookDetailRepository
import com.saba.core.models.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBookDetailUseCase @Inject constructor(private val bookDetailRepository: BookDetailRepository) {

    fun execute(isbn: String): Flow<Book> = flow<Book> {
        emit(bookDetailRepository.getBookByIsbn(isbn))
    }

}