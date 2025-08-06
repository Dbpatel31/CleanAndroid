package com.example.androidtask.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtask.domain.auth.LoginUseCase
import com.example.androidtask.domain.auth.OtpUseCase

class LoginViewModelFactory(
    private val loginUseCase: LoginUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
           return LoginViewModel(loginUseCase) as T
       }
        throw IllegalArgumentException("Unkown Viewmodel class")
    }
}