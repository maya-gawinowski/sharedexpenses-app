package com.example.sharedexpensesapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sharedexpensesapp.ui.screens.GroupScreen
import com.example.sharedexpensesapp.ui.screens.GroupViewModel
import com.example.sharedexpensesapp.ui.screens.WelcomScreen


enum class SharedExpenseScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Groups(title = R.string.group_page)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedExpenseBar(
    currentScreen: SharedExpenseScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedExpenseApp() {
    val navController = rememberNavController()
    //val viewModel: GroupViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SharedExpenseScreen.valueOf(
        backStackEntry?.destination?.route ?: SharedExpenseScreen.Start.name
    )
    Scaffold(
        topBar = {
            SharedExpenseBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SharedExpenseScreen.Start.name,
        ) {
            composable(route = SharedExpenseScreen.Start.name){
                WelcomScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(SharedExpenseScreen.Groups.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
            composable(route = SharedExpenseScreen.Groups.name){
                GroupScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        }
    }
}
