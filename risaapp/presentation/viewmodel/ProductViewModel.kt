package com.example.risaapp.presentation.viewmodel

import androidx.annotation.HalfFloat
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.risaapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : ViewModel() {

    val products = listOf(
        Product(1, "Tomatoes", "KSh 120.0/kg", "0712345678"),
        Product(2, "Sukuma Wiki", "KSh 80.0/bunch", "0798765432"),
        Product(3, "Broiler Eggs", "KSh 15.0/egg", "0700111222"),
        Product(4, "Spinach", "KSh 90.0/bunch", "074046232"),
        Product(5, "Cabbage", "KSh 25.0", "0703532052"),
        Product(6, "Chicken", "KSh 600.0", "0705235732"),
        Product(7, "Beans", "KSh 100.0/kg", "0772194954"),
        Product(8, "Sugarcane", "KSh 50.0/stem", "0700765682"),
    )

    val cart = mutableStateListOf<Product>()

    fun addToCart(product: Product) {
        if (!cart.contains(product)) {
            cart.add(product)
        }
    }

    fun removeFromCart(product: Product) {
        cart.remove(product)
    }

    fun clearCart() {
        cart.clear()
    }
}
