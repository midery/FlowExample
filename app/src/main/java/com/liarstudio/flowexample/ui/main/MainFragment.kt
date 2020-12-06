package com.liarstudio.flowexample.ui.main

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.liarstudio.flowexample.R
import com.liarstudio.flowexample.databinding.MainFragmentBinding
import com.liarstudio.flowexample.domain.Image
import com.liarstudio.flowexample.ui.detail.ImageDetailFragment
import com.liarstudio.flowexample.ui.main.adapter.MainAdapter
import com.liarstudio.flowexample.ui.main.data.MainEvent
import com.liarstudio.flowexample.ui.main.data.MainState
import com.liarstudio.flowexample.ui.main.decor.MainOffsetDecor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val binding by lazy { MainFragmentBinding.bind(requireView()) }
    private val adapter = MainAdapter { viewModel.onImageClicked(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.mainRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.mainRecycler.addItemDecoration(
            MainOffsetDecor(
                (Resources.getSystem().displayMetrics.density * 4).roundToInt()
            )
        )
        binding.mainRecycler.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModelImpl::class.java)
        viewLifecycleOwner.lifecycleScope.launch { initSubscriptions() }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.singleLiveEvent.collect(::executeSingleLiveEvent)
        }
    }

    private suspend fun initSubscriptions() {
        viewModel.state.collect(::renderState)
    }

    private fun renderState(state: MainState?) {
        state ?: return
        binding.mainProgress.isVisible = state.isLoading
        binding.mainRecycler.isVisible = !state.isLoading
        adapter.submitList(state.images)
    }

    private fun executeSingleLiveEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OpenImageDetail -> openImageDetailScreen(event.image)
            is MainEvent.ShowError -> Toast
                .makeText(requireContext(), R.string.network_error_message, Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun openImageDetailScreen(image: Image) {
        requireFragmentManager().beginTransaction().apply {
            replace(R.id.container, ImageDetailFragment.newInstance(image))
            addToBackStack(ImageDetailFragment.IMAGE_DETAIL_TAG)
            commit()
        }
    }
}