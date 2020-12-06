package com.liarstudio.flowexample.ui.main.data

import com.liarstudio.flowexample.domain.Image

data class MainState(
    val isLoading: Boolean,
    val images: List<Image> = listOf()
)