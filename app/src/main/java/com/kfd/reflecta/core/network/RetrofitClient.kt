package com.kfd.reflecta.core.network

import com.kfd.reflecta.data.auth.AuthApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
    // add more APIs here later
}
