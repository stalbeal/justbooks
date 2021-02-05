package com.saba.justbooks.com.saba.justbooks.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.saba.justbooks.com.saba.justbooks.home.models.ViewBook
import com.saba.justbooks.databinding.ItemBooksListBinding

class BooksAdapter(private val booksClickListener: BooksClickListener) :
    ListAdapter<ViewBook, BooksViewHolder>(listAdapterCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return BooksViewHolder(
            ItemBooksListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(getItem(position), booksClickListener)
    }

    companion object {
        val listAdapterCallback = (object :
            DiffUtil.ItemCallback<ViewBook>() {
            override fun areItemsTheSame(oldItem: ViewBook, newItem: ViewBook): Boolean =
                oldItem.googleBooksId == newItem.googleBooksId

            override fun areContentsTheSame(oldItem: ViewBook, newItem: ViewBook): Boolean =
                oldItem.hashCode() == newItem.hashCode()

            override fun getChangePayload(oldItem: ViewBook, newItem: ViewBook): Any? {

                return newItem
            }

        })
    }

}

