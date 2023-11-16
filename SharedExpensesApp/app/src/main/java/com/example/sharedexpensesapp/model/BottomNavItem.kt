package com.example.sharedexpensesapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sharedexpensesapp.R

private val Icons.Filled.Repeat: ImageVector
    get() {
        return R.drawable.baseline_repeat_24 as ImageVector
    }

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.ShoppingCart, "Spendings")
    object Search : BottomNavItem("search", Icons.Default.Repeat, "Balance")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
