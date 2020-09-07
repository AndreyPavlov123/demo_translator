package com.pavlov.demo_translator.core.service

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.pavlov.demo_translator.core.api.data.MeaningShortRoot
import com.pavlov.demo_translator.core.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class SearchService @Inject constructor(private val searchRepository: SearchRepository) {

    private val channel: ConflatedBroadcastChannel<String> = ConflatedBroadcastChannel()

    val searchResultFlow: Flow<PagingData<MeaningShortRoot>> = channel
        .asFlow()
        .debounce(1000)
        .flatMapLatest { searchRepository.search(it).flow }

    suspend fun search(query: String) = channel.send(query)
}