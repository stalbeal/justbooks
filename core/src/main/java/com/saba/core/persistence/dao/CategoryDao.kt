package com.saba.core.persistence.dao

import androidx.room.*
import com.saba.core.persistence.models.CATEGORY_COLUMN_NAME
import com.saba.core.persistence.models.DBCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<DBCategory>>

    @Query("SELECT * FROM $CATEGORY_COLUMN_NAME WHERE text = :text  LIMIT 1")
    fun get(text: String): Flow<DBCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg categories: DBCategory)

    @Delete
    fun delete(category: DBCategory)
}