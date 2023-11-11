package com.example.sharedexpensesapp.model

import androidx.annotation.StringRes
import com.example.sharedexpensesapp.R
import com.example.sharedexpensesapp.SharedExpenseScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Spendings : Screen(SharedExpenseScreen.Start.name, R.string.account_page)
    object Balance : Screen(SharedExpenseScreen.Groups.name, R.string.add_page)
    object Settings : Screen("settings", R.string.app_name)
}

val items = listOf(
    Screen.Spendings,
    Screen.Balance,
    Screen.Settings
)
