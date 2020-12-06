package com.liarstudio.flowexample.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.liarstudio.flowexample.R
import com.liarstudio.flowexample.domain.Image

class MainAdapter(
    private val onImageClickListener: (Image) -> Unit
) :
    ListAdapter<Image, MainAdapter.ViewHolder>(MainDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(parent.context, R.layout.list_item_image, null), onImageClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        itemView: View,
        private val onImageClickListener: (Image) -> Unit
    ) :
        RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.list_image_iv)

        fun bind(image: Image) {
            itemView.setOnClickListener { onImageClickListener(image) }
            Glide.with(imageView)
                .load(image.imageUrl)
                .apply(RequestOptions().centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }
}