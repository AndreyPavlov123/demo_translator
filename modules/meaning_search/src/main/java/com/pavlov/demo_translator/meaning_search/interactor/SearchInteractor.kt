package com.pavlov.demo_translator.meaning_search.interactor

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.pavlov.demo_translator.meaning_search.repository.SearchRepository
import com.pavlov.demo_translator.meaning_search.repository.dto.Word
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class SearchInteractor @Inject constructor(private val searchRepository: SearchRepository) {

    private val channel: ConflatedBroadcastChannel<String> = ConflatedBroadcastChannel()

    val searchResultFlow: Flow<PagingData<Word>> = channel
        .asFlow()
        .debounce(1000)
        .distinctUntilChanged()
        .filter { it.isNotBlank() }
        .flatMapLatest { searchRepository.search(it).flow }

    suspend fun search(query: String) = channel.send(query)
}