package com.example.androidtask.ui.auth.otp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.auth.OtpRequest
import com.example.androidtask.domain.auth.OtpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OtpViewModel(
    private val otpUseCase: OtpUseCase
) : ViewModel() {
    private val _verifyOtp= MutableStateFlow<String?>(null)
    val verifyOtp: StateFlow<String?> = _verifyOtp

    private val _isCheckVerify= MutableStateFlow<Boolean>(false)
    val isCheckVerify: StateFlow<Boolean> = _isCheckVerify

    fun verifyOtp(email:String, otp:String){
        viewModelScope.launch {
            val result= otpUseCase(OtpRequest(email, otp))

            result.
                    onSuccess {
                        _verifyOtp.value= it
                        _isCheckVerify.value=true
                    }

                .onFailure {
                    _verifyOtp.value= it.message
                }
        }
    }
}