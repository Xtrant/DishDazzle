package com.dundxn.dishdazzle.data.repository

import androidx.lifecycle.LiveData
import com.dundxn.dishdazzle.data.room.database.UserDao
import com.dundxn.dishdazzle.data.room.entity.User

class UserRepository(private val mUserDao: UserDao) {
    fun getUser(email: String, password: String): LiveData<User> = mUserDao.getUser(email, password)

    suspend fun addUser(user: User) {
        mUserDao.insertUser(user)
    }

    suspend fun deleteUser(email: String) {
        mUserDao.deleteUser(email)
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(mUserDao: UserDao) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(mUserDao)
            }.also { INSTANCE = it }
    }
}