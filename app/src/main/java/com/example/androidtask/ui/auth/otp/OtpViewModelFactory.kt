package com.example.androidtask.ui.auth.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtask.domain.auth.OtpUseCase

class OtpViewModelFactory(
    private  val  otpUseCase: OtpUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       if (modelClass.isAssignableFrom(OtpViewModel::class.java)){
           return  OtpViewModel(otpUseCase) as T
       }
        throw IllegalArgumentException("Unkown Viewmodel class")
    }
}