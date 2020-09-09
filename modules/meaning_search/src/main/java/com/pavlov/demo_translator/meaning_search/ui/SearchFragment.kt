package com.pavlov.demo_translator.meaning_search.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavlov.demo_translator.common.Navigator
import com.pavlov.demo_translator.common.NetworkLoadStateAdapter
import com.pavlov.demo_translator.meaning_search.R
import com.pavlov.demo_translator.meaning_search.databinding.FragmentSearchBinding
import com.pavlov.demo_translator.meaning_search.ui.adapter.WordAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject lateinit var meaningScreenNavigator: Navigator.MeaningScreen
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var pagingAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        pagingAdapter = WordAdapter {
            viewModel.meaningItemClicked(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchRecyclerView.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
            header = NetworkLoadStateAdapter(pagingAdapter),
            footer = NetworkLoadStateAdapter(pagingAdapter)
        )
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            viewModel.searchPagingFlow.collectLatest {
                pagingAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.searchRecyclerView.scrollToPosition(0) }
        }

        viewModel.navigateToMeaningScreen.observe(viewLifecycleOwner) {
            meaningScreenNavigator.navigate(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnCloseListener {
            viewModel.search("")
            true
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText ?: "")
                return true
            }
        })

        searchItem.expandActionView()

        return super.onCreateOptionsMenu(menu, inflater)
    }
}