package com.liarstudio.flowexample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liarstudio.flowexample.data.image.ImageRepository
import com.liarstudio.flowexample.ui.main.data.MainEvent
import com.liarstudio.flowexample.ui.main.data.MainState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

interface MainViewModel {
    val state: SharedFlow<MainState>
    val singleLiveEvent: SharedFlow<MainEvent>
}

class MainViewModelImpl : ViewModel(), MainViewModel {

    private val imageRepo = ImageRepository()

    override val state: MutableSharedFlow<MainState> = MutableSharedFlow(1)
    override val singleLiveEvent: MutableSharedFlow<MainEvent> = MutableSharedFlow(0)

    init {
        viewModelScope.launch {
            state.emit(MainState(isLoading = true, images = listOf()))
            delay(500)
            state.emit(MainState(isLoading = false, images = imageRepo.getImages()))
        }
    }
}
