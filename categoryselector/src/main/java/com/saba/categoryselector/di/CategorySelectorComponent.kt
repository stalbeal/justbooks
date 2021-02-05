package com.saba.categoryselector.di

import com.saba.categoryselector.CategorySelectorActivity
import com.saba.categoryselector.mvi.CategorySelectorReducer
import com.saba.core.di.CoreComponent
import dagger.Component

@ExperimentalStdlibApi
@CategorySelectorScope
@Component(dependencies = [CoreComponent::class], modules = [CategorySelectorModule::class])
interface CategorySelectorComponent {

    fun inject(categorySelectorActivity: CategorySelectorActivity)

    fun provideReducer() : CategorySelectorReducer
}