package com.kfd.reflecta.di

import android.content.Context
import com.kfd.reflecta.core.datastore.TokenStore
import com.kfd.reflecta.data.auth.AuthApi
import com.kfd.reflecta.data.auth.AuthRepository
import com.kfd.reflecta.core.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenStore(appContext: Context): TokenStore {
        return TokenStore(appContext)
    }

    @Provides
    @Singleton
    fun provideAuthApi(appContext: Context): AuthApi {
        return RetrofitClient.authApi(appContext)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepository(authApi)
    }
}
