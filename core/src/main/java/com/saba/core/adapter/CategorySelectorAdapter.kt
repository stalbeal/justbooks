package com.saba.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.saba.core.R

@ExperimentalStdlibApi
class CategorySelectorAdapter(
    private val colorList: Array<Int>,
    private val categoryClickListener: CategoryClickListener,
    @LayoutRes private val layoutId: Int = R.layout.item_category
) :
    ListAdapter<ViewCategory, CategorySelectorViewHolder>(listAdapterCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySelectorViewHolder {

        return CategorySelectorViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false),
            colorList
        )

    }

    override fun onBindViewHolder(holder: CategorySelectorViewHolder, position: Int) {
        holder.bind(getItem(position), categoryClickListener)
    }

    override fun onBindViewHolder(
        holder: CategorySelectorViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            holder.updateBackground(payloads.first() as ViewCategory)
        }
    }


    companion object {
        val listAdapterCallback = (object :
            DiffUtil.ItemCallback<ViewCategory>() {
            override fun areItemsTheSame(oldItem: ViewCategory, newItem: ViewCategory): Boolean =
                oldItem.text == newItem.text

            override fun areContentsTheSame(oldItem: ViewCategory, newItem: ViewCategory): Boolean =
                oldItem == newItem

            override fun getChangePayload(oldItem: ViewCategory, newItem: ViewCategory): Any? {

                return newItem
            }

        })
    }
}
