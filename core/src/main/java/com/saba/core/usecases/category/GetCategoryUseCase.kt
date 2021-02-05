package com.saba.core.usecases.category

import com.saba.core.di.CoreScope
import com.saba.core.repositories.category.CategoryRepository
import com.saba.core.usecases.category.model.Category
import javax.inject.Inject

@CoreScope
class GetCategoryUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    fun execute (id: String) : Category {

        return categoryRepository.getCategory(id)
    }
}

