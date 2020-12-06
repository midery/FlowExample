package com.liarstudio.flowexample.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.liarstudio.flowexample.domain.Image

class MainDiffCallback : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}