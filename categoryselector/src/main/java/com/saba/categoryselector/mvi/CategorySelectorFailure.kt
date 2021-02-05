package com.saba.categoryselector.mvi

import java.lang.Exception

sealed class CategorySelectorFailure(override val message: String?) : Exception(message) {
    class FetchError(message: String) : CategorySelectorFailure(message)
    object UnknownError : CategorySelectorFailure("There was an error please try again later")
}