package com.saba.justbooks.com.saba.justbooks.home.mvi

import com.saba.core.adapter.ViewCategory
import com.saba.core.base.ViewState
import com.saba.justbooks.com.saba.justbooks.home.models.ViewBook
import javax.inject.Inject

data class BooksHomeViewState @Inject constructor(
    override val isIdling: Boolean,
    val isLoading: Boolean,
    val books: Collection<ViewBook>?,
    val categories: Collection<ViewCategory>?,
    val error: BooksHomeFailure?
) : ViewState {

    companion object {
        fun init() = BooksHomeViewState(
            isIdling = true,
            isLoading = false,
            categories = null,
            books = null,
            error = null
        )
    }
}