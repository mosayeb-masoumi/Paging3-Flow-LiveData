package com.example.paging3application.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging3application.models.CharacterData
import com.example.paging3application.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService):ViewModel() {


    fun getListData(): LiveData<PagingData<CharacterData>> {

        return Pager (
            config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {CharacterPagingSource(apiService)}).liveData.cachedIn(viewModelScope)

    }
}