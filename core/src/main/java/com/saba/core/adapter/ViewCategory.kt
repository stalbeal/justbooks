package com.saba.core.adapter

import android.os.Parcelable
import com.saba.core.usecases.category.model.Category
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewCategory(val text: String, val image: Int, var isSelected: Boolean = false) : Parcelable {

    constructor(category: Category) : this(category.id, category.image)

}