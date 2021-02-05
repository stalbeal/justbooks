package com.saba.core.adapter

import com.saba.core.usecases.category.model.Category

data class ViewCategory(val text: String, val image: Int, var isSelected: Boolean = false) {

    constructor(category: Category) : this(category.id, category.image)

}