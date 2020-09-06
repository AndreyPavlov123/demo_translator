package com.pavlov.demo_translator.repository

import androidx.paging.*
import com.pavlov.demo_translator.api.Api
import com.pavlov.demo_translator.api.data.MeaningShortRoot

@ExperimentalPagingApi
class SearchPagingSource constructor(private val api: Api, private val query: String)
    : PagingSource<Int, MeaningShortRoot>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MeaningShortRoot> = try {
        val nextPageNumber = params.key ?: 1
        val searchResult = api.search(query, page = nextPageNumber, pageSize = params.loadSize)
        LoadResult.Page(searchResult.body()!!, null, nextPageNumber + 1)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

}

