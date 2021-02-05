package com.saba.justbooks.com.saba.justbooks.home.di

import com.saba.core.di.CoreComponent
import com.saba.justbooks.BooksHomeFragment
import com.saba.justbooks.com.saba.justbooks.home.mvi.BooksHomeReducer
import com.saba.justbooks.com.saba.justbooks.home.repositories.BookRepository
import com.saba.justbooks.com.saba.justbooks.home.usecases.GetBooksUseCase
import dagger.Component

@ExperimentalStdlibApi
@HomeScope
@Component(dependencies = [CoreComponent::class], modules = [HomeModule::class])
interface HomeComponent {

    fun inject(booksHomeFragment: BooksHomeFragment)

    fun provideHomeReducer(): BooksHomeReducer
    fun provideGetBooksUseCase(): GetBooksUseCase
    fun provideBookRepository(): BookRepository
}