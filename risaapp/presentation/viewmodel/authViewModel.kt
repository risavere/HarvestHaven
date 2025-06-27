package com.example.risaapp.presentation.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var isAuthenticated by mutableStateOf(false)

    val currentUser: FirebaseUser? get() = auth.currentUser

    fun onSignUpClicked() {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Invalid email"
            return
        }

        if (password.length < 10) {
            errorMessage = "Password must be at least 10 characters"
            return
        }

        if (password != confirmPassword) {
            errorMessage = "Passwords do not match"
            return
        }

        errorMessage = null

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isAuthenticated = true
                    errorMessage = null
                } else {
                    isAuthenticated = false
                    errorMessage = task.exception?.localizedMessage ?: "Sign Up failed"
                }
            }
    }

    fun onLoginClicked() {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Email and password are required"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Invalid email format"
            return
        }

        if (password.length < 10) {
            errorMessage = "Password must be at least 10 characters"
            return
        }

        errorMessage = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isAuthenticated = true
                    errorMessage = null
                } else {
                    isAuthenticated = false
                    errorMessage = task.exception?.localizedMessage ?: "Login failed"
                }
            }
    }

    fun logout() {
        auth.signOut()
        email = ""
        password = ""
        confirmPassword = ""
        isAuthenticated = false
        errorMessage = null
    }
}
