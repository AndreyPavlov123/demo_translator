package com.pavlov.demo_translator.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.pavlov.demo_translator.core.api.data.NumericId
import com.pavlov.demo_translator.core.service.SearchService
import com.pavlov.demo_translator.ui.model.SelectedMeaningModel
import com.pavlov.demo_translator.ui.tools.SingleLiveEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class SearchViewModel @ViewModelInject constructor(private val searchService: SearchService) : ViewModel() {

    val searchPagingFlow = searchService.searchResultFlow.cachedIn(viewModelScope)
    val openMeaningScreenEvent = SingleLiveEvent<SelectedMeaningModel>()

    fun search(query: String) {
        viewModelScope.launch {
            searchService.search(query.trim())
        }
    }

    fun meaningItemClicked(selectedMeaning: SelectedMeaningModel) {
        openMeaningScreenEvent.value = selectedMeaning
    }
}