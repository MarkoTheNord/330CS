package com.marko.cs330_project.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko.a330_cs.dao.UserDao
import com.marko.a330_cs.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    fun onLoginClick(onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUser(email, password)
            if (user != null) {
                onSuccess()
            } else {
                onError()
            }
        }
    }

    fun onRegisterClick(onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            println(email + " :: " + password + " :: " + confirmPassword)
            if (email != null || password != null || password != confirmPassword) {
                onError()
            }

            val user = userDao.getUser(email, password)
            if (user != null) {
                onError()
            }
            userDao.insert(User(email = email, password = password))
            onSuccess()
        }
    }
}
