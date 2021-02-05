package com.saba.justbooks.com.saba.justbooks.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saba.core.extensions.setImage
import com.saba.justbooks.com.saba.justbooks.home.models.ImageLink
import com.saba.justbooks.com.saba.justbooks.home.models.ViewBook
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
    }

    private fun setImage(view: ImageView, imageLink: ImageLink) {
        when {
            imageLink.smallThumbnail != null -> view.setImage(imageLink.smallThumbnail.replace("http", "https"))
            imageLink.thumbnail != null -> view.setImage(imageLink.thumbnail.replace("http", "https"))
            imageLink.small != null -> view.setImage(imageLink.small.replace("http", "https"))
            imageLink.medium != null -> view.setImage(imageLink.medium.replace("http", "https"))
            imageLink.large != null -> view.setImage(imageLink.large.replace("http", "https"))
            imageLink.extraLarge != null -> view.setImage(imageLink.extraLarge.replace("http", "https"))
        }
    }

}