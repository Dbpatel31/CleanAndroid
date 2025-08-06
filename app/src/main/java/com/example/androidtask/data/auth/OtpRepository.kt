package com.example.androidtask.data.auth

class OtpRepository {

    suspend fun verifyOtp(request: OtpRequest): Result<String> {
        val response = RetrofitClient.instance.verifyOtp(request)

        return try {
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Verify Successfully")
            } else {
                Result.failure(Throwable(response.errorBody()?.string()?: "Unkown error"))
            }
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }

    }
