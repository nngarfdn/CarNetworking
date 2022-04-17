package com.android.binarnetworking.ui

import androidx.lifecycle.ViewModel
import com.android.binarnetworking.data.repository.CarRepository

class DetailViewModel:ViewModel() {

    private val repository = CarRepository()

    fun getCarById(id: String) = repository.getCarById(id)
}