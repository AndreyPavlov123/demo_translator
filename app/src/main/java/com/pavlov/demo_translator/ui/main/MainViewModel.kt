package com.pavlov.demo_translator.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.pavlov.demo_translator.api.data.MeaningShortRoot
import com.pavlov.demo_translator.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class MainViewModel @ViewModelInject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val channel: ConflatedBroadcastChannel<PagingData<MeaningShortRoot>> = ConflatedBroadcastChannel()

    val searchPagingFlow = channel.asFlow()

    fun search(query: String) {
        viewModelScope.launch {
            searchRepository.search(query).flow.collectLatest {
                channel.send(it)
            }
        }
    }
}