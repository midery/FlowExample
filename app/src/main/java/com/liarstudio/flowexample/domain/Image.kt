package com.liarstudio.flowexample.domain

import java.io.Serializable

data class Image(
    val id: String = "",
    val farmId: String = "",
    val serverId: String = "",
    val secret: String = ""
) : Serializable {

    val imageUrl: String
        get() = "https://farm${farmId}.staticflickr.com/$serverId/${id}_${secret}.jpg"


}