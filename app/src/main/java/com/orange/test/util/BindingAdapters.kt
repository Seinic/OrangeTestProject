package com.orange.test.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orange.test.util.recycler.BindableAdapter

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
            if (recyclerView.adapter is BindableAdapter<*>) {
                (recyclerView.adapter as BindableAdapter<T>).setData(data)
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(view.context).load("https://image.tmdb.org/t/p/w500$url").into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("app:visibility")
        fun setVisibility(view: View, value: Boolean) {
            view.setVisibility(if (value) View.VISIBLE else View.GONE)
        }
    }
}