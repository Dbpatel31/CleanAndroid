package com.example.androidtask.ui.auth.register

object RegisterFormValidation {
    fun isFullNameValid(name: String):Boolean= name.isNotBlank()

    fun isEmailValid(email:String) : Boolean= email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password:String): Boolean{
        val lengthCheck= password.length>=6
        val uppercaseCheck= password.any{it.isUpperCase()}
        val lowercaseCheck= password.any{it.isLowerCase()}
        val specialCharCheck= password.any{!it.isLetterOrDigit()}

        return lengthCheck && uppercaseCheck && lowercaseCheck && specialCharCheck
    }

    fun isPhoneNumberValid(phone:String): Boolean= phone.length==10 && phone.all { it.isDigit() }

    fun isTermsAccepted(accepted : Boolean): Boolean= accepted
}