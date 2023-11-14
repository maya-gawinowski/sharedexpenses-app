package com.example.sharedexpensesapp.model

import androidx.annotation.StringRes
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.SharedExpenseScreen

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: Int,
    val label: String
) {
    object Spendings :
        Screen(
            SharedExpenseScreen.Start.name,
            R.string.account_page,
            R.drawable.baseline_shopping_cart_24,
            "Spendings"
        )

    object Balance :
        Screen(
            SharedExpenseScreen.Balance.name,
            R.string.balance_page,
            R.drawable.baseline_repeat_24,
            "Balance"
        )

    object Settings :
        Screen("settings", R.string.app_name, R.drawable.baseline_settings_24, "Settings")
}

val items = listOf(
    Screen.Spendings,
    Screen.Balance,
    Screen.Settings
)
