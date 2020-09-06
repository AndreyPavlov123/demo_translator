package com.pavlov.demo_translator.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.pavlov.demo_translator.api.Api
import javax.inject.Inject


@ExperimentalPagingApi
class SearchRepository @Inject constructor(private val api: Api)  {
    fun search(query: String) = Pager(PagingConfig(pageSize = 10, initialLoadSize = 10)) {
        SearchPagingSource(api, query)
    }
}