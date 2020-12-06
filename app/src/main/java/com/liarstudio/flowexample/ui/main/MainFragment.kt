package com.liarstudio.flowexample.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.liarstudio.flowexample.R
import com.liarstudio.flowexample.databinding.MainFragmentBinding
import com.liarstudio.flowexample.ui.main.data.MainEvent
import com.liarstudio.flowexample.ui.main.data.MainState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val binding by lazy { MainFragmentBinding.bind(requireView()) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModelImpl::class.java)
        viewLifecycleOwner.lifecycleScope.launch { initSubscriptions() }
    }

    private suspend fun initSubscriptions() {
        viewModel.state.collect(::renderState)
        viewModel.singleLiveEvent.collect(::executeSingleLiveEvent)
    }

    private fun renderState(state: MainState) {
        binding.mainProgress.isVisible = state.isLoading
    }

    private fun executeSingleLiveEvent(event: MainEvent) {
    }
}