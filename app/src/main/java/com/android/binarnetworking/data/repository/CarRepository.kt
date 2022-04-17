package com.android.binarnetworking.data.repository

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.binarnetworking.data.model.CarResponseItem
import com.android.binarnetworking.data.network.ApiClient
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarRepository {

    fun getAllCar() : MutableLiveData<List<CarResponseItem>?> {
        val data = MutableLiveData<List<CarResponseItem>?>()
        ApiClient.instance.getAllCar().enqueue(object : Callback<List<CarResponseItem>> {
            override fun onResponse(
                call: Call<List<CarResponseItem>>,
                response: Response<List<CarResponseItem>>
            ) {
                val body = response.body()
                val code = response.code()

                if (code == 200){
                    data.value = body
                }
            }

            override fun onFailure(call: Call<List<CarResponseItem>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    fun getCarById(id: String) : MutableLiveData<CarResponseItem?> {
        val data = MutableLiveData<CarResponseItem?>()
        ApiClient.instance.getCarById(id).enqueue(object : Callback<CarResponseItem> {
            override fun onResponse(
                call: Call<CarResponseItem>,
                response: Response<CarResponseItem>
            ) {
                val body = response.body()
                val code = response.code()

                if (code == 200){
                    data.value = body
                }
            }

            override fun onFailure(call: Call<CarResponseItem>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}