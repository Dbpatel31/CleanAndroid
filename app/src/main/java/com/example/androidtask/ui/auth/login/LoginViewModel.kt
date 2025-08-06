package com.example.androidtask.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtask.data.auth.LoginRequest
import com.example.androidtask.domain.auth.LoginUseCase
import com.example.androidtask.ui.auth.register.RegisterFormValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginResult= MutableStateFlow<String?>(null)
    val loginResult :StateFlow<String?> = _loginResult

    private val _validationResult= MutableLiveData<String>()
    val validationResult: LiveData<String>
        get()= _validationResult

    fun  validateForm(
        email: String,
        password: String
    ){
        when{
            !RegisterFormValidation.isEmailValid(email)-> _validationResult.value= "Invalid email"
            !RegisterFormValidation.isPasswordValid(password)-> _validationResult.value= "Password Must be at least 6 characters"
            else -> _validationResult.value= "Success"
        }
    }

     fun login(email: String, password:String){

        viewModelScope.launch {
            val result= loginUseCase(LoginRequest(email, password))
            result.onSuccess {
                _loginResult.value= it
            }

                .onFailure {
                    _loginResult.value= it.message
                }
        }

    }
}