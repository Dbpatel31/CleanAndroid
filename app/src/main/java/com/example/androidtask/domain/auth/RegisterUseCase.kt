package com.example.androidtask.domain.auth

import com.example.androidtask.data.auth.RegisterRepository
import com.example.androidtask.data.auth.RegisterRequest
import com.example.androidtask.data.auth.RegisterResponse
import com.example.androidtask.data.auth.SendOtpRequest

class RegisterUseCase(private val repository: RegisterRepository) {
    suspend operator fun invoke(request : RegisterRequest) : Result<String> {
       return  repository.registerUser(request)
    }

   suspend operator fun invoke(request:SendOtpRequest) : Result<String>{
       return repository.sendOtp(request)
   }
}