package com.example.paging3application.network


import com.example.paging3application.models.RickAndMortyList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    https://rickandmortyapi.com/api/character?page=1

    @GET("character")
    suspend fun getDataFromAPI(@Query("page") query: Int): RickAndMortyList

}