package com.pavlov.demo_translator.meaning_search.repository

import androidx.paging.*
import com.pavlov.demo_translator.meaning_search.repository.dto.Word

@ExperimentalPagingApi
class SearchPagingSource constructor(private val api: SearchApi, private val query: String) :
    PagingSource<Int, Word>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Word> = try {
        val page = params.key ?: 1
        val data = api.search(query, page = page, pageSize = params.loadSize)
        LoadResult.Page(
            data,
            if (page == 1) null else page - 1,
            if (data.size < params.loadSize) null else page + 1
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }
}

