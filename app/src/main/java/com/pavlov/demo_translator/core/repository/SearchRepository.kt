package com.pavlov.demo_translator.core.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.pavlov.demo_translator.core.api.Api
import javax.inject.Inject

@ExperimentalPagingApi
class SearchRepository @Inject constructor(private val api: Api)  {
    fun search(query: String) = Pager(PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)) {
        SearchPagingSource(api, query)
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}