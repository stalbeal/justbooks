package com.saba.categoryselector

import com.saba.categoryselector.mvi.CategorySelectorResult
import com.saba.categoryselector.mvi.CategorySelectorViewState
import com.saba.categoryselector.mvi.CategorySelectorWish
import com.saba.core.usecases.category.GetCategoriesUseCase
import com.saba.core.base.AbstractViewModel
import com.saba.core.base.CoroutineContextProvider
import com.saba.core.base.Reducer
import com.saba.core.usecases.category.GetCategoryUseCase
import com.saba.core.usecases.category.SaveCategoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CategorySelectorViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val saveCategoriesUseCase: SaveCategoriesUseCase,
    override val reducer: Reducer<@JvmSuppressWildcards CategorySelectorResult,@JvmSuppressWildcards  CategorySelectorViewState>,
    override val coroutineContextProvider: CoroutineContextProvider
) :
    AbstractViewModel<CategorySelectorWish, CategorySelectorResult, CategorySelectorViewState>(
        CategorySelectorViewState.init()
    ) {
    override suspend fun getResult(wish: CategorySelectorWish): Flow<CategorySelectorResult> {
        return when (wish) {
            is CategorySelectorWish.GetCategories -> getCategories()
            is CategorySelectorWish.SaveSelection -> saveSelectedCategories(wish)
            is CategorySelectorWish.SelectCategory -> updateCategory(wish)

        }
    }

    private fun updateCategory(wish: CategorySelectorWish.SelectCategory): Flow<CategorySelectorResult> =
        flow {
            val categories = wish.categoriesList.toList().map {

                if (it.text == wish.text) {
                    it.copy(
                        isSelected = !it.isSelected
                    )
                } else {
                    it
                }
            }
            emit(CategorySelectorResult.CategorySelected(categories))

        }

    private fun getCategories(): Flow<CategorySelectorResult> =
        flow {
            emit(
                CategorySelectorResult.CategoriesObtained(
                    getCategoriesUseCase.execute()
                )
            )
        }

    private fun saveSelectedCategories(wish: CategorySelectorWish.SaveSelection): Flow<CategorySelectorResult> {
        return flow {
            saveCategoriesUseCase.execute(wish.categoriesList.filter {
                it.isSelected
            }.map {
                getCategoryUseCase.execute(it.text)
            })
            emit(CategorySelectorResult.CategoriesSaved)
        }.flowOn(coroutineContextProvider.backgroundDispatcher)
    }
}