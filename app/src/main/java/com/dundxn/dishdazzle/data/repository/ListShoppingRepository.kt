package com.dundxn.dishdazzle.data.repository

import androidx.lifecycle.LiveData
import com.dundxn.dishdazzle.data.room.database.ListShoppingDao
import com.dundxn.dishdazzle.data.room.entity.ListShopping

class ListShoppingRepository(private val mListShoppingDao: ListShoppingDao) {

    fun getListShoppingByEmail(email: String): LiveData<List<ListShopping>> =
        mListShoppingDao.getListShopping(email)

    suspend fun setListShopping(listShopping: ListShopping, isList : Boolean) {
        listShopping.isList = isList
        mListShoppingDao.updateListShopping(listShopping)
    }

    companion object {
        @Volatile
        private var INSTANCE: ListShoppingRepository? = null
        fun getInstance(mListShoppingDao: ListShoppingDao)=
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ListShoppingRepository(mListShoppingDao)
            }.also { INSTANCE = it }
    }

}