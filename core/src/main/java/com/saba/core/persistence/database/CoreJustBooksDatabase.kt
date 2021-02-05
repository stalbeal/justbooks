package com.saba.core.persistence.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saba.core.persistence.dao.CategoryDao
import com.saba.core.persistence.models.DBCategory

@Database(entities = [DBCategory::class], version = 1)
abstract class CoreJustBooksDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

}