package com.example.risaapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.risaapp.data.model.Product
import com.example.risaapp.presentation.viewmodel.AuthViewModel
import com.example.risaapp.presentation.viewmodel.CartViewModel
import com.example.risaapp.presentation.viewmodel.ProductViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ProductListScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel(),
    productViewModel: ProductViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val products = productViewModel.products
    val cartItems = cartViewModel.cartItems

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF29AB87))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Available Products",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Button(onClick = { navController.navigate("cart") }) {
                Text("View Cart (${cartItems.size})")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onAdd = {
                        cartViewModel.addToCart(product)
                        Toast.makeText(context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
                    },
                    onContact = {
                        Toast.makeText(context, "Contact: ${product.farmerContact}", Toast.LENGTH_LONG).show()
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = {
                authViewModel.logout()
                navController.navigate("login") {
                    popUpTo("productlist") { inclusive = true }
                }
            }
        ) {
            Text("Logout", color = Color.White)
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onAdd: () -> Unit,
    onContact: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                product.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black // Ensure visibility
            )
            Text(
                "Ksh ${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black // Ensure visibility
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = onAdd, modifier = Modifier.weight(1f)) {
                    Text("Add to Cart")
                }
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = onContact, modifier = Modifier.weight(1f)) {
                    Text("Contact Farmer")
                }
            }
        }
    }
}

