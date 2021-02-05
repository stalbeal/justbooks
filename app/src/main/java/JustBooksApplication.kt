package com.saba.justbooks

import android.app.Application
import com.saba.core.di.CoreComponent
import com.saba.core.di.CoreComponentProvider
import com.saba.core.di.DaggerCoreComponent

class JustBooksApplication : Application(),
    CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    private val BASE_GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/"

    override fun provideCoreComponent(): CoreComponent {
        coreComponent = DaggerCoreComponent.factory().create(this, BASE_GOOGLE_BOOKS_API_URL)
        return coreComponent
    }

}