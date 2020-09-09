package com.pavlov.demo_translator.meaning_search.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

@ExperimentalPagingApi
class SearchRepository @Inject constructor(private val api: SearchApi)  {
    fun search(query: String) = Pager(PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)) {
        SearchPagingSource(api, query)
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}