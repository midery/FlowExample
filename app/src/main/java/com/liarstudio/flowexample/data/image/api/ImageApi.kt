package com.liarstudio.flowexample.data.image.api

import com.liarstudio.flickr_image_viewer.data.base.api.ServerUrls
import com.liarstudio.flowexample.data.image.response.GetImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET(ServerUrls.Image.GET_RECENT)
    suspend fun getImages(
        @Query("api_key") apiKey: String = "da9d38d3dee82ec8dda8bb0763bf5d9c",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1
    ): GetImagesResponse
}