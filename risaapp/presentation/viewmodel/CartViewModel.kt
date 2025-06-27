package com.example.risaapp.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.risaapp.data.model.Product

class CartViewModel : ViewModel() {

    private val _cartItems = mutableStateListOf<Product>()
    val cartItems: List<Product> = _cartItems

    // Add product to cart if not already added
    fun addToCart(product: Product) {
        if (!_cartItems.contains(product)) {
            _cartItems.add(product)
        }
    }

    // Remove a product from the cart
    fun removeFromCart(product: Product) {
        _cartItems.remove(product)
    }

    // Clear all products from the cart
    fun clearCart() {
        _cartItems.clear()
    }
}
