package com.kfd.reflecta.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kfd.reflecta.core.datastore.TokenStore
import com.kfd.reflecta.data.auth.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

sealed class SplashState {
    data object Loading : SplashState()
    data object Authenticated : SplashState()
    data object Unauthenticated : SplashState()
}

class SplashViewModel(
    private val tokenStore: TokenStore,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState> = _state

    init {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            val accessToken = tokenStore.accessToken.firstOrNull()
            val sessionId = tokenStore.sessionId.firstOrNull()
            val refreshToken = tokenStore.refreshToken.firstOrNull()
            if (!accessToken.isNullOrBlank() && !sessionId.isNullOrBlank()) {
                _state.value = SplashState.Authenticated
            } else {
                _state.value = SplashState.Unauthenticated
            }
        }
    }
}


//package com.kfd.reflecta.presentation.splash
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.kfd.reflecta.core.datastore.TokenStore
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.firstOrNull
//import kotlinx.coroutines.launch
//
//sealed class SplashState {
//    data object Loading : SplashState()
//    data object Authenticated : SplashState()
//    data object Unauthenticated : SplashState()
//}
//
//class SplashViewModel(
//    private val tokenStore: TokenStore
//) : ViewModel() {
//
//    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
//    val state: StateFlow<SplashState> = _state
//
//    init {
//        checkAuth()
//    }
//
//    private fun checkAuth() {
//        viewModelScope.launch {
//            val token = tokenStore.accessToken.firstOrNull()
//            val sessionId = tokenStore.sessionId.firstOrNull()
//            val refreshToken = tokenStore.refreshToken.firstOrNull()
//
//            // TODO should be a request to api/me/ for example, if error 401 code ->
//            // TODO -> request to refresh with session, on error -> _state.value = SplashState.Unauthenticated
////            if (!token.isNullOrBlank()) {
////
////                _state.value = SplashState.Authenticated
////            } else {
////                _state.value = SplashState.Unauthenticated
////            }
//        }
//    }
//}
