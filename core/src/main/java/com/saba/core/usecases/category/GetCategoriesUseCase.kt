package com.saba.core.usecases.category

import com.saba.core.di.CoreScope
import com.saba.core.repositories.category.CategoryRepository
import com.saba.core.usecases.category.model.Category
import javax.inject.Inject

@CoreScope
class GetCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    fun execute(): Collection<Category> {
        return categoryRepository.getCategories()
    }
}

