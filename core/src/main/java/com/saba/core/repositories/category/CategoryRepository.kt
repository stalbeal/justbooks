package com.saba.core.repositories.category

import com.saba.core.usecases.category.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getCategories() : Collection<Category>

    fun getCategory(id: String) : Category

    fun updateCategory(id: String): Category

    suspend fun saveSelectedCategories(categories: Collection<Category>)

    fun getSelectedCategories() : Flow<Collection<Category>>

}

