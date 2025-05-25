package com.kfd.reflecta.data.auth.dto

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val sessionId: String
)