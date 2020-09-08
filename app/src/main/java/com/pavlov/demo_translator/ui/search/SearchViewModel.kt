package com.pavlov.demo_translator.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.pavlov.demo_translator.core.api.data.Word
import com.pavlov.demo_translator.core.service.SearchService
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class SearchViewModel @ViewModelInject constructor(private val searchService: SearchService) : ViewModel() {

    val searchPagingFlow = searchService.searchResultFlow.cachedIn(viewModelScope)
    val openMeaningScreenEvent = SingleLiveEvent<Pair<Word, Int>>()

    fun search(query: String) {
        if (query.isBlank())
            return
        viewModelScope.launch {
            searchService.search(query.trim())
        }
    }

    fun meaningItemClicked(data: Word, selectedMeaning: Int) {
        openMeaningScreenEvent.value = Pair(data, selectedMeaning)
    }
}