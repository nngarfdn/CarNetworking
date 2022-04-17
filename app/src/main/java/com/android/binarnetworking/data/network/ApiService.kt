package com.android.binarnetworking.data.network

import com.android.binarnetworking.data.auth.LoginRequest
import com.android.binarnetworking.data.auth.RegisterRequest
import com.android.binarnetworking.data.model.CarResponseItem
import com.android.binarnetworking.data.model.LoginResponseItem
import com.android.binarnetworking.data.model.RegisterResponseItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface ApiService {

    @GET("admin/car")
    fun getAllCar(): Call<List<CarResponseItem>>

    @GET("admin/car/{id}")
    fun getCarById(@Path("id")id: String): Call<CarResponseItem>

    @POST("admin/auth/register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponseItem>

    @POST("admin/auth/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponseItem>

}

object ApiClient {
    private const val BASE_URL = "https://rent-cars-api.herokuapp.com/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }

}
