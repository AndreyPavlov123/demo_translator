package com.pavlov.demo_translator.meaning_search.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.cachedIn
import com.pavlov.demo_translator.common.SingleLiveEvent
import com.pavlov.demo_translator.meaning_common.ui.model.SelectedMeaningModel
import com.pavlov.demo_translator.meaning_search.interactor.SearchInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class SearchViewModel @ViewModelInject constructor(private val searchInteractor: SearchInteractor) : ViewModel() {

    val searchPagingFlow = searchInteractor.searchResultFlow.cachedIn(viewModelScope)
    val openMeaningScreenEvent = SingleLiveEvent<SelectedMeaningModel>()

    fun search(query: String) {
        viewModelScope.launch {
            searchInteractor.search(query.trim())
        }
    }

    fun meaningItemClicked(selectedMeaning: SelectedMeaningModel) {
        openMeaningScreenEvent.value = selectedMeaning
    }
}