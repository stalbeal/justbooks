package com.saba.categoryselector.di

import com.saba.categoryselector.mvi.CategorySelectorReducer
import com.saba.categoryselector.mvi.CategorySelectorResult
import com.saba.categoryselector.mvi.CategorySelectorViewState
import com.saba.core.base.Reducer
import dagger.Module
import dagger.Provides

@Module
object CategorySelectorModule {

    @CategorySelectorScope
    @Provides
    fun provideCategorySelectorReducer(categorySelectorReducer: CategorySelectorReducer) : Reducer<@JvmSuppressWildcards CategorySelectorResult, @JvmSuppressWildcards CategorySelectorViewState> {
        return categorySelectorReducer
    }
}