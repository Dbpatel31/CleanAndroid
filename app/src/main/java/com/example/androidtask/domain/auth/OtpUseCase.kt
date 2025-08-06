package com.example.androidtask.domain.auth

import com.example.androidtask.data.auth.OtpRepository
import com.example.androidtask.data.auth.OtpRequest
import com.example.androidtask.data.auth.RegisterRepository

class OtpUseCase(private val repository: OtpRepository) {

    suspend operator fun invoke(request: OtpRequest):Result<String>{
        return repository.verifyOtp(request)
    }
}