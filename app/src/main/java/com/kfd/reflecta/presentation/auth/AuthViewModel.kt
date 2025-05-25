package com.kfd.reflecta.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.reflecta.core.datastore.TokenStore
import com.kfd.reflecta.core.network.ApiResult
import com.kfd.reflecta.data.auth.AuthRepository
import com.kfd.reflecta.data.auth.dto.RegistrationRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
    private val tokenStore: TokenStore
) : ViewModel() {

    private val _registerState = MutableStateFlow<ApiResult<Unit>?>(null)
    val registerState: StateFlow<ApiResult<Unit>?> = _registerState

    fun register(email: String, username: String, password: String) {
        viewModelScope.launch {
            _registerState.value = ApiResult.Loading
            val result = repository.register(
                RegistrationRequest(email, username, password)
            )
            when (result) {
                is ApiResult.Success -> {
                    tokenStore.saveTokens(
                        result.data.accessToken,
                        result.data.refreshToken,
                        result.data.sessionId
                    )
                    _registerState.value = ApiResult.Success(Unit)
                }
                is ApiResult.Error -> {
                    _registerState.value = result
                }
                is ApiResult.Loading -> TODO()
            }
        }
    }
}
