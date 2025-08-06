package com.example.androidtask.domain.auth

import com.example.androidtask.data.auth.LoginRepository
import com.example.androidtask.data.auth.LoginRequest

class LoginUseCase(private val  repository: LoginRepository) {

    suspend operator fun invoke(request: LoginRequest):Result<String>{
        return repository.login(request)
    }
}