package com.example.androidtask.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtask.domain.auth.RegisterUseCase

class RegisterViewModelFactory (
    private val registerUseCase: RegisterUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if(modelClass.isAssignableFrom(RegisterViewModel::class.java)){
           return  RegisterViewModel(registerUseCase) as T
       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
