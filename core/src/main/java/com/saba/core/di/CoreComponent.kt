package com.saba.core.di

import android.app.Application
import android.content.Context
import com.saba.core.base.CoroutineContextProvider
import com.saba.core.di.module.CoreModule
import com.saba.core.di.module.NetworkModule
import com.saba.core.di.module.PersistenceModule
import com.saba.core.network.services.BookService
import com.saba.core.persistence.dao.CategoryDao
import com.saba.core.repositories.category.CategoryRepository
import com.saba.core.usecases.category.GetCategoriesUseCase
import com.saba.core.usecases.category.GetCategoryUseCase
import com.saba.core.usecases.category.GetSelectedCategoriesUseCase
import com.saba.core.usecases.category.SaveCategoriesUseCase
import dagger.BindsInstance
import dagger.Component

@CoreScope
@Component(
    modules = [
        CoreModule::class,
        PersistenceModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {

    @Component.Factory
    interface Builder {

        fun create(
            @BindsInstance context: Context,
            @BindsInstance baseGoogleApiUrl: String
        ): CoreComponent
    }

    fun inject(application: Application)

    fun provideCoroutineContext(): CoroutineContextProvider
    fun provideCategoryRepository(): CategoryRepository
    fun provideCategoryDao(): CategoryDao
    fun provideGetCategoryUseCase(): GetCategoryUseCase
    fun provideGetCategoriesUseCase(): GetCategoriesUseCase
    fun provideSaveCategoriesUseCase(): SaveCategoriesUseCase
    fun provideGetSelectedCategoriesUseCase(): GetSelectedCategoriesUseCase
    fun provideBookService(): BookService


}
