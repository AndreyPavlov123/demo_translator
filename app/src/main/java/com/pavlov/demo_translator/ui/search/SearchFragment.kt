package com.pavlov.demo_translator.ui.search

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
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
import com.pavlov.demo_translator.R
import com.pavlov.demo_translator.databinding.FragmentSearchBinding
import com.pavlov.demo_translator.ui.meaning.MeaningFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var pagingAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        pagingAdapter = SearchAdapter { data, _ ->

//            val url = "https:" + data.meanings!!.first()!!.soundUrl
//            GlobalScope.launch {
//                    MediaPlayer().apply {
//                        setDataSource(url)
//                        prepare() // might take long! (for buffering, etc)
//                        start()
//                    }
//            }

            MeaningFragment.newInstance(data).show(childFragmentManager, "MeaningFragment")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
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
                progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { searchRecyclerView.scrollToPosition(0) }
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