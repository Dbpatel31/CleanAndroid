package com.example.androidtask.data.auth

class RegisterRepository {
    suspend fun registerUser(request: RegisterRequest): Result<String>{
        return try {
            val response= RetrofitClient.instance.registerUser(request)

            if (response.isSuccessful){
              Result.success(response.body()?.message ?: "Registerd Successfully")
            }
            else{
                Result.failure(Throwable(response.errorBody()?.string()?: "Unknown error"))
            }
        }
        catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun sendOtp(request: SendOtpRequest): Result<String>{
        return try{
          val response= RetrofitClient.instance.sendOtp(request)

            if (response.isSuccessful){
                Result.success(response.body()?.message ?: "Otp Sent Successfully")

            }
            else{
                Result.failure(Throwable(response.errorBody()?.string()?: "Unknown error"))
            }
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }
}