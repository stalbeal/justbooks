package com.saba.bookdetail.di

import com.saba.bookdetail.repositories.BookDetailRepository
import com.saba.bookdetail.repositories.BookDetailRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
object BookDetailModule {

    @BookDetailScope
    @Provides
    fun provideBookDetailRepository(bookDetailRepositoryImpl: BookDetailRepositoryImpl): BookDetailRepository {
        return bookDetailRepositoryImpl
    }

}
