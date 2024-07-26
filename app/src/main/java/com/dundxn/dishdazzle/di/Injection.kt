package com.dundxn.dishdazzle.di

import android.app.Application
import android.content.Context
import com.dundxn.dishdazzle.data.pref.UserPreference
import com.dundxn.dishdazzle.data.pref.datastore
import com.dundxn.dishdazzle.data.repository.FavoriteRepository
import com.dundxn.dishdazzle.data.repository.ListShoppingRepository
import com.dundxn.dishdazzle.data.repository.RecipeRepository
import com.dundxn.dishdazzle.data.repository.UserRepository
import com.dundxn.dishdazzle.data.retrofit.ApiConfig
import com.dundxn.dishdazzle.data.room.database.DishDazzleDatabase
import com.dundxn.dishdazzle.data.room.entity.User
import com.dundxn.dishdazzle.ui.ViewModelFactory

object Injection {
    fun provideViewModelFactory(mApplication: Application) : ViewModelFactory {
        val apiService = ApiConfig.getApiService()
        val database = DishDazzleDatabase.getInstance(mApplication)
        val mUserDao = database.userDao()
        val mFavDao = database.favFoodDao()
        val mListDao = database.listShoppingDao()
        val recipeRepository = RecipeRepository.getInstance(apiService)
        val userRepository = UserRepository.getInstance(mUserDao)
        val favoriteRepository = FavoriteRepository.getInstance(mFavDao)
        val listShoppingRepository = ListShoppingRepository.getInstance(mListDao)
        val preferences = UserPreference.getInstance(mApplication.datastore)



        return ViewModelFactory(recipeRepository, preferences, userRepository, favoriteRepository, listShoppingRepository )
    }
}