package com.liarstudio.flowexample.ui.main.data

import com.liarstudio.flowexample.domain.Image

sealed class MainEvent {
    data class OpenImageDetail(val image: Image) : MainEvent()
    class ShowError : MainEvent()
}
