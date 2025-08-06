package com.example.androidtask.data.auth

data class LoginResponse(
    val message: String,
    val username: User
)

data class User(
    val _id: String,
    val username: String,
    val email:  String
)