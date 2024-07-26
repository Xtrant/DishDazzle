package com.dundxn.dishdazzle.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dundxn.dishdazzle.data.repository.FavoriteRepository
import com.dundxn.dishdazzle.data.room.entity.FavoriteFood
import com.dundxn.dishdazzle.utils.showToast
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> = _isFav

    fun getFavFoodByEmail(email: String): LiveData<List<FavoriteFood>> =
        favoriteRepository.getFavFoodByEmail(email)

    private fun removeFavFood(favFood : FavoriteFood) {
        isFav(false)
        viewModelScope.launch {
            favoriteRepository.deleteFavFood(favFood)
        }
    }

    private fun addFavFood(favFood: FavoriteFood) {
        isFav(true)
        viewModelScope.launch {
            favoriteRepository.insertFavFood(favFood)
        }
    }

    fun isFav(isFav : Boolean) {
        _isFav.value = isFav
    }

    fun updateFavFood(favFood: FavoriteFood, context: Context) {
        if (isFav.value != true) {
            addFavFood(favFood)
            showToast(context, "Added to Favorite")
        } else {
            removeFavFood(favFood)
            showToast(context, "Removed from Favorite")
        }
    }
}
