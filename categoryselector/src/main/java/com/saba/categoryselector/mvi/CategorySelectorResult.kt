package com.saba.categoryselector.mvi

import com.saba.core.adapter.ViewCategory
import com.saba.core.usecases.category.model.Category
import com.saba.core.base.Reducer
import com.saba.core.base.Result
import javax.inject.Inject

sealed class CategorySelectorResult : Result {
    object OnLoading : CategorySelectorResult()
    object CategoriesSaved: CategorySelectorResult()

    data class CategoriesObtained(val categories: Collection<Category>) : CategorySelectorResult()
    data class CategorySelected(val categories: Collection<ViewCategory>) : CategorySelectorResult()
    data class Error(val error: CategorySelectorFailure) : CategorySelectorResult()


}

class CategorySelectorReducer @Inject constructor() : Reducer<CategorySelectorResult, CategorySelectorViewState> {
    @ExperimentalStdlibApi
    override suspend fun reduce(
        state: CategorySelectorViewState,
        from: CategorySelectorResult
    ): CategorySelectorViewState {
        return when (from) {
            is CategorySelectorResult.OnLoading -> state.copy(
                isIdling = false,
                isLoading = true,
                shownCategories = null,
                error = null
            )
            is CategorySelectorResult.CategoriesObtained -> state.copy(
                isIdling = false,
                isLoading = false,
                shownCategories = from.categories.map {
                    ViewCategory(it)
                },
                error = null
            )
            is CategorySelectorResult.CategorySelected -> state.copy(
                isIdling = false,
                isLoading = false,
                shownCategories = from.categories,
                error = null
            )
            is CategorySelectorResult.CategoriesSaved -> state.copy(
                isIdling = false,
                isLoading = false,
                categoriesSaved = true,
                error = null
            )
            is CategorySelectorResult.Error -> state.copy(
                isIdling = false,
                isLoading = false,
                shownCategories = null,
                error = from.error
            )
        }
    }
}

