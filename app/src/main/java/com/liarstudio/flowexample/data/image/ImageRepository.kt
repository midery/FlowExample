package com.liarstudio.flowexample.data.image

import com.liarstudio.flowexample.data.base.api.ApiProvider
import com.liarstudio.flowexample.domain.Image

class ImageRepository {

    private val imageApi by lazy { ApiProvider.imageApi }

    suspend fun getImages(): List<Image> {
        return imageApi.getImages().transform()
    }
}