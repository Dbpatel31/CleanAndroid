package com.example.androidtask.data.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServices {
    @Headers("Content-Type: application/json")
    @POST("auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<RegisterResponse>

    @Headers("Content-Type: application/json")
    @POST("auth/send-otp")
    suspend fun sendOtp(@Body request: SendOtpRequest) : Response<SendOtpResponse>

    @Headers("Content-Type: application/json")
    @POST("auth/verify-otp")
    suspend fun  verifyOtp(@Body request: OtpRequest) : Response<OtpResponse>

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest) : Response<LoginResponse>
}