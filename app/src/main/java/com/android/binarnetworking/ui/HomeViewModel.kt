package com.android.binarnetworking.ui

import androidx.lifecycle.ViewModel
import com.android.binarnetworking.data.repository.CarRepository

class HomeViewModel: ViewModel() {
    val repository = CarRepository()

    fun getCars() = repository.getAllCar()
}