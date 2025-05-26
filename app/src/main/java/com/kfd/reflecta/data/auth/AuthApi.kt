package com.kfd.reflecta.data.auth

import com.kfd.reflecta.data.auth.dto.AuthResponse
import com.kfd.reflecta.data.auth.dto.LoginRequest
import com.kfd.reflecta.data.auth.dto.RefreshRequest
import com.kfd.reflecta.data.auth.dto.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("/api/auth/register")
    suspend fun register(@Body request: RegistrationRequest) : AuthResponse

    @POST("/api/auth/refresh")
    suspend fun refresh(@Body request: RefreshRequest) : AuthResponse
}