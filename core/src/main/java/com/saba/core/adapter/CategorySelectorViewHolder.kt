package com.saba.core.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.saba.core.R
import kotlinx.android.extensions.LayoutContainer
import java.util.*

@ExperimentalStdlibApi
class CategorySelectorViewHolder(
    view: View,
    private val colorList: Array<Int>
) : RecyclerView.ViewHolder(view),
    LayoutContainer {

    private val locale = Locale.getDefault()

    override val containerView: View?
        get() = itemView

    private val categoryText: TextView = view.findViewById(R.id.tvTitle)

    private val image: ImageView = view.findViewById(R.id.ivImage)

    private val container: ConstraintLayout = view.findViewById(R.id.clContainer)

    fun bind(
        category: ViewCategory,
        categoryClickListener: CategoryClickListener
    ) {
        categoryText.text = category.text.toLowerCase(locale).capitalize(locale)
        image.setImageDrawable(ContextCompat.getDrawable(image.context, category.image))
        updateBackground(category)
        itemView.setOnClickListener {
            categoryClickListener.onClick(category.text)
        }
    }

    fun updateBackground(category: ViewCategory) {
        if (category.isSelected) {
            container.setBackgroundColor(container.context.getColor(R.color.primaryLightColor))
        } else {
            container.setBackgroundColor(container.context.getColor(getColor()))
        }
    }

    private fun getColor(): Int {
        val max = colorList.size - 1
        return colorList.toIntArray()[getCategoryColorPosition(adapterPosition, max)]

    }

    private fun getCategoryColorPosition(position: Int, listSize: Int): Int {

        if (position < listSize) {
            return position
        }

        return getCategoryColorPosition(position - listSize, listSize)
    }

}

