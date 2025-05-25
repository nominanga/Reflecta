package com.kfd.reflecta.data.auth.dto

data class RegistrationRequest(
    val email: String,
    val username: String,
    val password: String
)
