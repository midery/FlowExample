package com.liarstudio.flowexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liarstudio.flowexample.data.image.ImageRepository
import com.liarstudio.flowexample.domain.Image
import com.liarstudio.flowexample.ui.main.data.MainEvent
import com.liarstudio.flowexample.ui.main.data.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception

interface MainViewModel {
    val state: SharedFlow<MainState?>
    val singleLiveEvent: SharedFlow<MainEvent>

    fun onImageClicked(image: Image)
}

class MainViewModelImpl : ViewModel(), MainViewModel {

    private val imageRepo = ImageRepository()

    override val state: MutableStateFlow<MainState?> = MutableStateFlow(null)
    override val singleLiveEvent: MutableSharedFlow<MainEvent> = MutableSharedFlow(0)

    init {
        viewModelScope.launch {
            state.emit(MainState(isLoading = true))
            delay(500)
            try {
                val images = imageRepo.getImages()
                state.emit(MainState(isLoading = false, images = images))
            } catch (exception: Exception) {
                state.emit(MainState(isLoading = false))
                singleLiveEvent.emit(MainEvent.ShowError())
            }
        }
    }

    override fun onImageClicked(image: Image) {
        viewModelScope.launch { singleLiveEvent.emit(MainEvent.OpenImageDetail(image)) }
    }
}
