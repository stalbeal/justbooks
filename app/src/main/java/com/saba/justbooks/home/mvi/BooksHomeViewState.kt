package com.saba.justbooks.home.mvi

import android.os.Parcelable
import com.saba.core.adapter.ViewCategory
import com.saba.core.base.ViewState
import com.saba.core.models.ViewBook
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.io.Serializable

@Parcelize
data class BooksHomeViewState(
    override val isIdling: Boolean,
    val isLoading: Boolean,
    val books: @RawValue Collection<ViewBook>?,
    val categories: @RawValue Collection<ViewCategory>?,
    val error: BooksHomeFailure?
) : ViewState, Parcelable {

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

