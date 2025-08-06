package com.example.androidtask.ui.auth.register

import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.auth.RegisterRequest
import com.example.androidtask.data.auth.SendOtpRequest
import com.example.androidtask.domain.auth.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase : RegisterUseCase) : ViewModel() {
    private val _validationResult= MutableLiveData<String>()
    val validationResult: LiveData<String>get()= _validationResult

    private val _registerResult= MutableStateFlow<String?>(null)
    val registerResult:StateFlow<String?> = _registerResult

    private val _sendotpResult= MutableStateFlow<Boolean>(false)
    val senotpResult: StateFlow<Boolean> = _sendotpResult
    fun validateForm(
        name: String,
        email: String,
        password: String,
        phone: String,
        termsAccepted : Boolean
    ){
        when{
            !RegisterFormValidation.isFullNameValid(name)-> _validationResult.value= "Name is required"
            !RegisterFormValidation.isEmailValid(email) -> _validationResult.value= "Invalid email"
            !RegisterFormValidation.isPasswordValid(password)-> _validationResult.value= "Password Must be at least 6 characters"
            !RegisterFormValidation.isPhoneNumberValid(phone)-> _validationResult.value= "Invalid Phone number"
            !RegisterFormValidation.isTermsAccepted(termsAccepted)-> _validationResult.value= "Please accept terms"
            else -> _validationResult.value= "Success"
        }
    }

    fun registerUser(username: String, email: String, password: String){
        viewModelScope.launch {
            val result=  registerUseCase(RegisterRequest(username, email, password))
            result
                .onSuccess {
                    _registerResult.value=it
                    val otpRequest= registerUseCase(SendOtpRequest(email))
                    otpRequest.onSuccess {
                        _sendotpResult.value= true
                    }
                        .onFailure {
                            _registerResult.value= "Failed to send OTP"
                        }
                }
                .onFailure { _registerResult.value=it.message }
        }
    }
}