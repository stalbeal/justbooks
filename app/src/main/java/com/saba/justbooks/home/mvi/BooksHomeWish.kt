package com.saba.justbooks.home.mvi

import com.saba.categoryselector.mvi.CategorySelectorWish
import com.saba.core.adapter.ViewCategory
import com.saba.core.base.Wish


sealed class BooksHomeWish : Wish {

    object GetCategories : BooksHomeWish()

    object GetBooks : BooksHomeWish()

    data class GetBooksByCategory(val category: String) : BooksHomeWish()

}

