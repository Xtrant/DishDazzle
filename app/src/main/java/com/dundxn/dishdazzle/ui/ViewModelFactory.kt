package com.dundxn.dishdazzle.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dundxn.dishdazzle.data.pref.UserPreference
import com.dundxn.dishdazzle.data.repository.FavoriteRepository
import com.dundxn.dishdazzle.data.repository.ListShoppingRepository
import com.dundxn.dishdazzle.data.repository.RecipeRepository
import com.dundxn.dishdazzle.data.repository.UserRepository
import com.dundxn.dishdazzle.di.Injection
import com.dundxn.dishdazzle.ui.auth.AuthViewModel
import com.dundxn.dishdazzle.ui.favorite.FavoriteViewModel
import com.dundxn.dishdazzle.ui.recipe.RecipeViewModel
import com.dundxn.dishdazzle.ui.shoppinglist.ShoppingListViewModel

class ViewModelFactory(
    private val recipeRepository: RecipeRepository,
    private val preference: UserPreference,
    private val userRepository: UserRepository,
    private val favoriteRepository: FavoriteRepository,
    private val listShoppingRepository: ListShoppingRepository,
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RecipeViewModel::class.java) ->
                RecipeViewModel(recipeRepository, preference) as T

            modelClass.isAssignableFrom(AuthViewModel::class.java) ->
                AuthViewModel(userRepository, preference) as T

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->
                FavoriteViewModel(favoriteRepository) as T

            modelClass.isAssignableFrom(ShoppingListViewModel::class.java) ->
                ShoppingListViewModel(listShoppingRepository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(mApplication: Application): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                Injection.provideViewModelFactory(mApplication)
            }.also { INSTANCE = it }
    }

}