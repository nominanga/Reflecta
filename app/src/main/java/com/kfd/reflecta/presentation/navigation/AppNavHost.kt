package com.kfd.reflecta.presentation.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kfd.reflecta.presentation.auth.AuthViewModel
import com.kfd.reflecta.presentation.auth.RegisterScreen
//import com.kfd.reflecta.presentation.auth.LoginScreen
//import com.kfd.reflecta.presentation.auth.RegisterScreen
//import com.kfd.reflecta.presentation.home.HomeScreen
import com.kfd.reflecta.presentation.splash.SplashScreen
import com.kfd.reflecta.presentation.splash.SplashViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    splashViewModel: SplashViewModel,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController, splashViewModel)
        }
//        composable("login") {
//            LoginScreen(navController)
//        }
        composable("register") {
            RegisterScreen(navController, authViewModel)
        }
//        composable("home") {
//            HomeScreen(navController)
//        }
    }
}