package com.dundxn.dishdazzle.data.repository

import androidx.lifecycle.LiveData
import com.dundxn.dishdazzle.data.room.database.FavFoodDao
import com.dundxn.dishdazzle.data.room.entity.FavoriteFood

class FavoriteRepository(private val mFavoriteFoodDao: FavFoodDao) {

    fun getFavFoodByEmail(email: String): LiveData<List<FavoriteFood>> =
        mFavoriteFoodDao.getFavFood(email)

    suspend fun insertFavFood(favFood: FavoriteFood) {
        mFavoriteFoodDao.insertFavFood(favFood)
    }

    suspend fun deleteFavFood(favFood: FavoriteFood) {
        mFavoriteFoodDao.deleteFavFood(favFood)
    }


    companion object {
        @Volatile
        private var INSTANCE: FavoriteRepository? = null
        fun getInstance(mFavoriteFoodDao: FavFoodDao)=
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FavoriteRepository(mFavoriteFoodDao)
            }.also { INSTANCE = it }
    }
}