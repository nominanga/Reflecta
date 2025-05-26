package com.kfd.reflecta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kfd.reflecta.core.datastore.TokenStore
import com.kfd.reflecta.core.network.RetrofitClient
import com.kfd.reflecta.data.auth.AuthRepository
import com.kfd.reflecta.presentation.auth.AuthViewModel
import com.kfd.reflecta.presentation.navigation.AppNavHost
import com.kfd.reflecta.presentation.splash.SplashViewModel
import com.kfd.reflecta.ui.theme.ReflectaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReflectaTheme {
                val tokenStore = remember { TokenStore(applicationContext) }
                val authRepository = remember { AuthRepository(RetrofitClient.authApi) }

                val splashViewModel = remember { SplashViewModel(tokenStore) }
                val authViewModel = remember { AuthViewModel(authRepository, tokenStore) }

                val navController = rememberNavController()
                AppNavHost(
                    navController,
                    splashViewModel,
                    authViewModel,
                )
            }
        }
    }
}