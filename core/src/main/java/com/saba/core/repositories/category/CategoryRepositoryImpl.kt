package com.saba.core.repositories.category

import com.saba.core.persistence.dao.CategoryDao
import com.saba.core.persistence.models.DBCategory
import com.saba.core.repositories.category.CategoryRepository
import com.saba.core.usecases.category.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryDao: CategoryDao) :
    CategoryRepository {
    override fun getCategories(): Collection<Category> {
        return Category.getValues()
    }

    override fun getCategory(id: String): Category {
        return Category.getValues().first {
            it.id == id
        }
    }

    override fun updateCategory(id: String): Category {
        return Category.getValues().first {
            it.id == id
        }
    }

    override suspend fun saveSelectedCategories(categories: Collection<Category>) {
        categories.map {
            categoryDao.insert(it.toDBCategory())
        }
    }

    override fun getSelectedCategories() :  Flow<Collection<Category>>{
        return categoryDao.getAll().map {categories ->
            categories.map {
                it.toCategory()
            }
        }
    }

    private fun Category.toDBCategory(): DBCategory {
        return DBCategory(id, image)
    }

    private fun DBCategory.toCategory(): Category {
        return getCategory(text)
    }

}