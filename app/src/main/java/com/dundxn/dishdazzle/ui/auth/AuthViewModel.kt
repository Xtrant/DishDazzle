package com.dundxn.dishdazzle.ui.auth

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dundxn.dishdazzle.data.pref.UserPreference
import com.dundxn.dishdazzle.data.repository.UserRepository
import com.dundxn.dishdazzle.data.room.entity.User
import com.dundxn.dishdazzle.utils.showToast
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) :
    ViewModel() {

    private val _isUserAdded = MutableLiveData<Boolean>()
    val isUserAdded : LiveData<Boolean> = _isUserAdded

    fun getUser(email: String, password: String): LiveData<User> {
        return userRepository.getUser(email, password)
    }

    fun addUser(user: User, context: Context) {
        viewModelScope.launch {
            try {
                userRepository.addUser(user)
                showToast(context, "Account Successfully Created")
                _isUserAdded.value = true
            } catch (e: Exception) {
                _isUserAdded.value = false
                if (e is SQLiteConstraintException) {
                    showToast(context, "Email already exists")
                } else {
                    showToast(context, "Failed to insert user: ${e.message}")
                }
            }
        }
    }

    fun deleteUser(email: String) {
        viewModelScope.launch {
            userRepository.deleteUser(email)
        }
    }

    fun saveSession(email: String, name: String, isLogin: Boolean) {
        viewModelScope.launch {
            userPreference.saveSession(email, name, isLogin)
        }
    }

    fun deleteSession() {
        viewModelScope.launch {
            userPreference.clearSession()
        }
    }

    fun getEmail(): LiveData<String> {
        return userPreference.getEmail().asLiveData()
    }

    fun isLogin(): LiveData<Boolean> {
        return userPreference.isLogin().asLiveData()
    }


}