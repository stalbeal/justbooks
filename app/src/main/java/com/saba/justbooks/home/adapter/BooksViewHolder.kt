package com.saba.justbooks.home.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.saba.core.extensions.setImage
import com.saba.core.models.ImageLink
import com.saba.core.models.ViewBook
import com.saba.justbooks.databinding.ItemBooksListBinding

class BooksViewHolder(private val binding: ItemBooksListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ViewBook, booksClickListener: BooksClickListener) {
        //binding.ivBookImage =
        binding.tvBookTitle.text = item.title
        binding.tvBookAuthor.text = item.authors.joinToString(", ")
        item.averageRating?.let {
            binding.rbBookRating.visibility = View.VISIBLE
            binding.rbBookRating.max = 5
            binding.rbBookRating.rating = it.toFloat()
            item.ratingsCount?.let { count ->
                binding.tvRatingCount.text = count.toString()
                binding.tvRatingCount.visibility = View.VISIBLE
            }
        }
        item.imageLinks?.let {
            setImage(binding.ivBookImage, it)

        }
        itemView.setOnClickListener {
            booksClickListener.onClick(item)
        }
    }

    private fun setImage(view: ImageView, imageLink: ImageLink) {
        when {
            imageLink.smallThumbnail != null -> view.setImage(imageLink.smallThumbnail!!)
            imageLink.thumbnail != null -> view.setImage(imageLink.thumbnail!!)
            imageLink.small != null -> view.setImage(imageLink.small!!)
            imageLink.medium != null -> view.setImage(imageLink.medium!!)
            imageLink.large != null -> view.setImage(imageLink.large!!)
            imageLink.extraLarge != null -> view.setImage(imageLink.extraLarge!!)
        }
    }

}