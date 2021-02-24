package com.saba.bookdetail.di

import com.saba.bookdetail.BookDetailFragment
import com.saba.core.di.CoreComponent
import dagger.Component

@ExperimentalStdlibApi
@BookDetailScope
@Component(dependencies = [CoreComponent::class], modules = [BookDetailModule::class])
interface BookDetailComponent {

    fun inject(bookDetailFragment: BookDetailFragment)
}