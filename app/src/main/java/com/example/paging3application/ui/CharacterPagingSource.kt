package com.example.paging3application.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3application.models.CharacterData
import com.example.paging3application.network.ApiService
import java.lang.Exception
import javax.inject.Inject

class CharacterPagingSource (private val apiService: ApiService) : PagingSource<Int, CharacterData>() {


    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }

        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {

        return try {
            val nextPage = params.key ?: 1
            val response = apiService.getDataFromAPI(nextPage)

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1)

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}