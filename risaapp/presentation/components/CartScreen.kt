package com.example.risaapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.risaapp.data.model.Product
import com.example.risaapp.presentation.viewmodel.CartViewModel
import com.example.risaapp.ui.theme.FruityYellow

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FruityYellow)
            .padding(16.dp)
    ) {
        Text(
            text = "My Cart",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        if (cartViewModel.cartItems.isEmpty()) {
            Text("Cart is empty.", color = Color.Black)
        } else {
            LazyColumn {
                items(cartViewModel.cartItems) { product ->
                    CartItemCard(
                        product = product,
                        onRemove = {
                            cartViewModel.removeFromCart(product)
                            Toast.makeText(context, "${product.name} removed from cart", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    cartViewModel.clearCart()
                    Toast.makeText(context, "Cart cleared", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear Cart")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Checkout successful!", Toast.LENGTH_SHORT).show()
                    cartViewModel.clearCart()
                    navController.popBackStack("dashboard", inclusive = false)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Checkout")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = { navController.popBackStack("dashboard", inclusive = false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Products", color = Color.Black)
        }
    }
}

@Composable
fun CartItemCard(
    product: Product,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.name, style = MaterialTheme.typography.titleLarge)
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRemove, modifier = Modifier.fillMaxWidth()) {
                Text("Remove from Cart")
            }
        }
    }
}
