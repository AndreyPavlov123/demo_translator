package com.pavlov.demo_translator.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var searchView: SearchView
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var pagingAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        pagingAdapter = SearchAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.searchRecyclerView.adapter = pagingAdapter
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycleScope.launch {
            viewModel.searchPagingFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView

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
        return super.onCreateOptionsMenu(menu, inflater)
    }
}