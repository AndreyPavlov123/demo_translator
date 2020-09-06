package com.pavlov.demo_translator.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavlov.demo_translator.api.data.MeaningShortRoot
import com.pavlov.demo_translator.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)

        val pagingAdapter = SearchAdapter(object : DiffUtil.ItemCallback<MeaningShortRoot>() {
            override fun areItemsTheSame(
                oldItem: MeaningShortRoot,
                newItem: MeaningShortRoot
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MeaningShortRoot,
                newItem: MeaningShortRoot
            ): Boolean = oldItem.id == newItem.id
        })

        lifecycleScope.launch {
            viewModel.searchPagingFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
        binding.searchRecyclerView.adapter = pagingAdapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        viewModel.search("работа")
    }

}