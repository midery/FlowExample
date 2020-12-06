package com.liarstudio.flowexample.data.image.response

import com.google.gson.annotations.SerializedName
import com.liarstudio.flickr_image_viewer.data.base.Transformable
import com.liarstudio.flowexample.domain.Image

class ImageListResponse(
    @SerializedName("photo") val photo: List<ImageResponse>?
) : Transformable<List<Image>> {

    override fun transform(): List<Image> = photo?.map { it.transform() } ?: emptyList()
}