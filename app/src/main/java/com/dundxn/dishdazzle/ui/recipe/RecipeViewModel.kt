package com.dundxn.dishdazzle.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dundxn.dishdazzle.data.pref.UserPreference
import com.dundxn.dishdazzle.data.repository.RecipeRepository
import com.dundxn.dishdazzle.data.response.SearchResponse
import com.dundxn.dishdazzle.result.Result
import kotlinx.coroutines.launch

class RecipeViewModel(private val recipeRepository: RecipeRepository, private val preference: UserPreference): ViewModel() {

    private val _resultRecipe = MutableLiveData<Result<SearchResponse>>()
    val resultRecipe : LiveData<Result<SearchResponse>> = _resultRecipe

    fun searchRecipe(search: String) {
        viewModelScope.launch {
            _resultRecipe.value = Result.Loading
            _resultRecipe.value = recipeRepository.listSearch(search)
        }
    }

    fun detailRecipe(idMeal: String) {
        viewModelScope.launch {
            _resultRecipe.value = Result.Loading
            _resultRecipe.value = recipeRepository.detailRecipe(idMeal)
        }
    }

    fun getName(): LiveData<String> {
        return preference.getName().asLiveData()
    }

}