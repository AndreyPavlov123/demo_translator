package com.pavlov.demo_translator.core.repository

import androidx.paging.*
import com.pavlov.demo_translator.core.api.Api
import com.pavlov.demo_translator.core.api.MeaningShortRoot

@ExperimentalPagingApi
class SearchPagingSource constructor(private val api: Api, private val query: String)
    : PagingSource<Int, MeaningShortRoot>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MeaningShortRoot> = try {
        val nextPageNumber = params.key ?: 1
        val searchResult = api.search(query, page = nextPageNumber, pageSize = params.loadSize)
        val data = searchResult.body()!!
        LoadResult.Page(data, null, if (data.size < params.loadSize) null else nextPageNumber + 1)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}

