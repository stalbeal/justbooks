package com.saba.bookdetail

import com.saba.core.base.ViewState
import com.saba.core.models.ViewBook

data class BookDetailState(override val isIdling: Boolean,
                      val isLoading: Boolean,
                      val book: ViewBook?,
                      val error: Exception?
) : ViewState {

    companion object {
        fun init() = BookDetailState(
            isIdling = true,
            isLoading = false,
            book = null,
            error = null
        )
    }
}