package com.dundxn.dishdazzle.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dundxn.dishdazzle.data.repository.ListShoppingRepository
import com.dundxn.dishdazzle.data.room.entity.ListShopping
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val listShoppingRepository: ListShoppingRepository) : ViewModel() {

    fun getListShopping(email: String) {
        listShoppingRepository.getListShoppingByEmail(email)
    }

    fun deleteAll() {

    }

    fun insertListShopping(listShopping: ListShopping) {
        viewModelScope.launch {
            listShoppingRepository.setListShopping(listShopping, true)
        }
    }

    fun deleteListShopping(listShopping: ListShopping) {
        viewModelScope.launch {
            listShoppingRepository.setListShopping(listShopping, false)
        }
    }
}