package com.android.binarnetworking.data.repository

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.android.binarnetworking.R
import com.android.binarnetworking.data.auth.LoginRequest
import com.android.binarnetworking.data.auth.RegisterRequest
import com.android.binarnetworking.data.model.LoginResponseItem
import com.android.binarnetworking.data.model.RegisterResponseItem
import com.android.binarnetworking.data.network.ApiClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    fun register(register: RegisterRequest) : MutableLiveData<RegisterResponseItem?> {
        val data = MutableLiveData<RegisterResponseItem?>()
        ApiClient.instance.registerUser(register).enqueue(object : Callback<RegisterResponseItem> {
            override fun onResponse(
                call: Call<RegisterResponseItem>,
                response: Response<RegisterResponseItem>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<RegisterResponseItem>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    fun login(login: LoginRequest): MutableLiveData<LoginResponseItem?> {
        val data = MutableLiveData<LoginResponseItem?>()
        ApiClient.instance.loginUser(login).enqueue(object : Callback<LoginResponseItem>{
            override fun onResponse(
                call: Call<LoginResponseItem>,
                response: Response<LoginResponseItem>
            ) {
                val responseBody = response.body()
                if (response.code() == 201){
                    data.value = responseBody
                }
            }

            override fun onFailure(call: Call<LoginResponseItem>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }
}