package com.saba.core.usecases.category

import com.saba.core.di.CoreScope
import com.saba.core.repositories.category.CategoryRepository
import com.saba.core.usecases.category.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@CoreScope
class GetSelectedCategoriesUseCase @Inject constructor(private val categoryRepository: CategoryRepository) {

    fun execute(): Flow<Collection<Category>> {
        return categoryRepository.getSelectedCategories().map {
            if(it.isEmpty()){
                throw NullPointerException()
            }
            it
        }
    }
}