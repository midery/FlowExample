package com.liarstudio.flowexample.data.base.api

import com.liarstudio.flickr_image_viewer.data.base.api.ServerUrls
import com.liarstudio.flowexample.data.image.api.ImageApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ServerUrls.BASE_URL)
            .build()
    }

    val imageApi by lazy { retrofit.create(ImageApi::class.java) }
}