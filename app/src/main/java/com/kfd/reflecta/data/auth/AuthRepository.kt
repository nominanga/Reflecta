package com.kfd.reflecta.data.auth

import com.kfd.reflecta.core.network.ApiResult
import com.kfd.reflecta.data.auth.dto.AuthResponse
import com.kfd.reflecta.data.auth.dto.LoginRequest
import com.kfd.reflecta.data.auth.dto.RefreshRequest
import com.kfd.reflecta.data.auth.dto.RegistrationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(
    private val api: AuthApi
) {
    suspend fun register(request: RegistrationRequest): ApiResult<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.register(request)
                ApiResult.Success(response)
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }

    suspend fun login(request: LoginRequest): ApiResult<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.login(request)
                ApiResult.Success(response)
            } catch (e: Exception) {
                ApiResult.Error(e)
            }
        }
    }


}
