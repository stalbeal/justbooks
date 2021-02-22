package com.saba.justbooks.home.di

import com.saba.core.base.Reducer
import com.saba.justbooks.home.mvi.BooksHomeReducer
import com.saba.justbooks.home.mvi.BooksHomeResult
import com.saba.justbooks.home.mvi.BooksHomeViewState
import com.saba.justbooks.home.repositories.BookRepository
import com.saba.justbooks.home.repositories.BookRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @HomeScope
    fun provideHomeReducer(booksHomeReducer: BooksHomeReducer):
            Reducer<@JvmSuppressWildcards BooksHomeResult,
                    @JvmSuppressWildcards BooksHomeViewState> = booksHomeReducer

    @Provides
    @HomeScope
    fun provideBooksRepository(bookRepository: BookRepositoryImpl): BookRepository = bookRepository

}

