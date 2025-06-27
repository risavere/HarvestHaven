package com.example.risaapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.risaapp.presentation.components.ForgotPasswordScreen
import com.example.risaapp.presentation.components.LoginScreen
import com.example.risaapp.presentation.components.SignUpScreen
import com.example.risaapp.presentation.components.ProductListScreen
import com.example.risaapp.presentation.components.CartScreen
import com.example.risaapp.presentation.components.DashboardScreen
import com.example.risaapp.presentation.viewmodel.AuthViewModel
import com.example.risaapp.presentation.viewmodel.CartViewModel
import com.example.risaapp.presentation.viewmodel.ProductViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val productViewModel: ProductViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = viewModel() // ✅ Add this here

    NavHost(navController = navController, startDestination = "signup") {

        composable("signup") {
            SignUpScreen(navController, authViewModel)
        }
        composable("dashboard") {
            DashboardScreen(navController)
        }
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("forgot") {
            ForgotPasswordScreen(navController)
        }
        composable("productlist") {
            ProductListScreen(
                navController = navController,
                cartViewModel = cartViewModel,
                productViewModel = productViewModel,
                authViewModel = authViewModel
            )
        }
        composable("cart") {
            CartScreen(
                navController = navController,
                cartViewModel = cartViewModel
            )
        }
        composable("forgot") {
            ForgotPasswordScreen(navController = navController)
        }
    }
}
