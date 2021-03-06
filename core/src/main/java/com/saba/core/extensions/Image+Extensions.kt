package com.saba.core.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

interface ImageLoaderCallback {

    fun onSuccess()
    fun onFailure()
}

fun ImageView.setImage(url: String) {

    Glide.with(context).load(url.replace("http", "https")).into(this)
}

