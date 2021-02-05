package com.saba.categoryselector.mvi

import com.saba.core.adapter.ViewCategory
import com.saba.core.base.ViewState
import javax.inject.Inject

data class CategorySelectorViewState @Inject constructor(
    override val isIdling: Boolean,
    val isLoading: Boolean,
    val shownCategories: Collection<ViewCategory>?,
    val categoriesSaved: Boolean,
    val error: CategorySelectorFailure?
) : ViewState {
    companion object {
        fun init() = CategorySelectorViewState(
            isIdling = true,
            isLoading = false,
            shownCategories = null,
            categoriesSaved = false,
            error = null
        )
    }
}

