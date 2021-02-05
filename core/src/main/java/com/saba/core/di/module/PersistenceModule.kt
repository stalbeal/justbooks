package com.saba.core.di.module

import android.content.Context
import androidx.room.Room
import com.saba.core.di.CoreScope
import com.saba.core.persistence.dao.CategoryDao
import com.saba.core.persistence.database.CoreJustBooksDatabase
import dagger.Module
import dagger.Provides

@Module
object PersistenceModule {

    @CoreScope
    @Provides
    fun provideDatabase(context: Context): CoreJustBooksDatabase {
        return Room.databaseBuilder(
            context,
            CoreJustBooksDatabase::class.java,
            "core_just_books_database"
        ).build()
    }

    @CoreScope
    @Provides
    fun provideCategoryDao(database: CoreJustBooksDatabase) : CategoryDao {
        return database.categoryDao()
    }
}

