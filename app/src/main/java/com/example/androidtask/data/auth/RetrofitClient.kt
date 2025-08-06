package com.example.androidtask.data.auth

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL=  "https://mock-api.calleyacd.com/api/"

    private val loggingInterceptor= HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY
    }

    private  val client= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    val instance : ApiServices by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build().create(ApiServices::class.java)
    }

}