package com.dundxn.dishdazzle.data.retrofit

import com.dundxn.dishdazzle.data.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search.php")
    suspend fun searchRecipe(
        @Query("s") search: String
    ): SearchResponse

    @GET("lookup.php")
    suspend fun detailRecipe(
        @Query("i") idFood: String
    ): SearchResponse

    @GET("random.php")
    suspend fun listRecipe(
    ): SearchResponse
}