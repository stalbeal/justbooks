package com.saba.core.di.module

import com.saba.core.base.CoroutineContextProvider
import com.saba.core.di.CoreScope
import com.saba.core.repositories.category.CategoryRepository
import com.saba.core.repositories.category.CategoryRepositoryImpl
import com.saba.core.usecases.category.GetCategoryUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
object CoreModule {

    @CoreScope
    @Provides
    fun provideCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository {
        return categoryRepositoryImpl
    }

    @CoreScope
    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        CoroutineContextProvider(Dispatchers.Main, Dispatchers.IO)

}

