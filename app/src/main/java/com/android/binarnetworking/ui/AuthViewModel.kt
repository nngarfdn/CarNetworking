package com.android.binarnetworking.ui

import androidx.lifecycle.ViewModel
import com.android.binarnetworking.data.auth.LoginRequest
import com.android.binarnetworking.data.auth.RegisterRequest
import com.android.binarnetworking.data.repository.AuthRepository

class AuthViewModel : ViewModel() {
    private val repository= AuthRepository()

    fun register(register: RegisterRequest) = repository.register(register)
    fun login(login: LoginRequest) = repository.login(login)
}