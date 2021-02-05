package com.saba.categoryselector.mvi

import com.saba.core.adapter.ViewCategory
import com.saba.core.base.Wish

sealed class CategorySelectorWish : Wish {
    object GetCategories : CategorySelectorWish()

    data class SelectCategory(
        val text: String,
        val categoriesList: Collection<ViewCategory>
    ) : CategorySelectorWish()

    data class SaveSelection(
        val categoriesList: Collection<ViewCategory>
    ) : CategorySelectorWish()

}