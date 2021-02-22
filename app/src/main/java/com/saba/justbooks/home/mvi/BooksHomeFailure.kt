package com.saba.justbooks.home.mvi

import java.lang.Exception

sealed class BooksHomeFailure (override val message: String?) : Exception(message) {
    class FetchError(message: String) : BooksHomeFailure(message)
    object UnknownError : BooksHomeFailure("There was an error please try again later")
}
