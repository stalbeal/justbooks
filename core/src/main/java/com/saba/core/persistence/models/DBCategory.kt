package com.saba.core.persistence.models

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.PrimaryKey

@Entity(tableName = CATEGORY_COLUMN_NAME)
data class DBCategory(
    @PrimaryKey val text: String,
    val image: Int
)

const val CATEGORY_COLUMN_NAME = "category"

