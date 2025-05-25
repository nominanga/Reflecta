package com.kfd.reflecta.data.auth.dto

data class RefreshRequest(
    val refreshToken: String,
    val sessionId: String
)
