package com.liarstudio.flowexample.ui.detail

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.liarstudio.flowexample.R
import com.liarstudio.flowexample.databinding.ImageDetailFragmentBinding
import com.liarstudio.flowexample.domain.Image

class ImageDetailFragment : Fragment(R.layout.image_detail_fragment) {

    companion object {
        const val IMAGE_DETAIL_TAG = "ImageDetailFragment"
        private const val IMAGE_DETAIL_URL_EXTRA = "IMAGE_DETAIL_URL"

        fun newInstance(image: Image): ImageDetailFragment = ImageDetailFragment().apply {
            arguments = bundleOf(IMAGE_DETAIL_URL_EXTRA to image.imageUrl)
        }
    }

    private val binding by lazy { ImageDetailFragmentBinding.bind(requireView()) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imageUrl = arguments?.getString(IMAGE_DETAIL_URL_EXTRA) ?: return
        Glide.with(binding.imageDetailIv)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imageDetailIv)
    }
}