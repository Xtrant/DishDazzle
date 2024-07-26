package com.dundxn.dishdazzle.data.repository

import com.dundxn.dishdazzle.data.response.SearchResponse
import com.dundxn.dishdazzle.data.retrofit.ApiService
import com.dundxn.dishdazzle.data.room.database.FavFoodDao
import com.dundxn.dishdazzle.data.room.database.ListShoppingDao
import com.dundxn.dishdazzle.result.Result
import retrofit2.HttpException

class RecipeRepository(private val apiService: ApiService) {
    suspend fun listSearch(search: String): Result<SearchResponse> {
        return try {
            val response = apiService.searchRecipe(search)
            Result.Success(response)

        } catch (e: HttpException) {
            Result.Error(e.message())
        }
    }

    suspend fun detailRecipe(idMeal: String): Result<SearchResponse> {
        return try {
            val response = apiService.detailRecipe(idMeal)
            Result.Success(response)
        } catch (e: HttpException) {
            Result.Error(e.message())
        }
    }

    companion object {
        @Volatile
        private var instance: RecipeRepository? = null
        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: RecipeRepository(apiService)
            }.also { instance = it }
    }
}