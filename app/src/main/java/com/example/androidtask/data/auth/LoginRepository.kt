package com.example.androidtask.data.auth

class LoginRepository {

    suspend fun  login(request: LoginRequest) : Result<String>{


        return try {
            val response= RetrofitClient.instance.login(request)

            if (response.isSuccessful){
                Result.success(response.body()?.message ?: "Login Successfull")
            }
            else{
                Result.failure(Throwable(response.errorBody()?.string()?: "Unkown Error"))
            }
        }
        catch (e:Exception){
          Result.failure(e)
        }
    }
}