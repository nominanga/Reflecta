package com.kfd.reflecta.core.network

import com.kfd.reflecta.core.datastore.TokenStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStore: TokenStore
) : Interceptor {

    private val publicPaths = listOf(
        "/api/auth/login",
        "/api/auth/register",
        "/api/auth/refresh"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        val isPublic = publicPaths.any { it.equals(path, ignoreCase = true) }
                || path.startsWith("/media")

        val newRequest = if (!isPublic) {
            val token = tokenStore.getAccessToken()
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }

        return chain.proceed(newRequest)
    }
}
