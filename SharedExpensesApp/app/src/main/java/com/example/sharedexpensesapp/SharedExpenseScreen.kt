package com.example.sharedexpensesapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sharedexpensesapp.datasource.DataSource
import com.example.sharedexpensesapp.model.GroupItem
import com.example.sharedexpensesapp.model.screens
import com.example.sharedexpensesapp.ui.screens.AccountScreen
import com.example.sharedexpensesapp.ui.screens.AddExpenseScreen
import com.example.sharedexpensesapp.ui.screens.AddGroupScreen
import com.example.sharedexpensesapp.ui.screens.BalanceScreen
import com.example.sharedexpensesapp.ui.screens.GroupScreen
import com.example.sharedexpensesapp.ui.screens.JoinGroupScreen
import com.example.sharedexpensesapp.ui.screens.SettingsScreen
import com.example.sharedexpensesapp.ui.screens.WelcomeScreen


enum class SharedExpenseScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Groups(title = R.string.group_page),
    AddGroup(title = R.string.add_page),
    Join(title = R.string.join_page),
    Account(title = R.string.account_page),
    Balance(title = R.string.balance_page),
    AddExpense(title = R.string.add_expense_page),
    Settings(title = R.string.settings)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedExpenseBar(
    modifier: Modifier = Modifier,
    currentScreen: SharedExpenseScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    navAccount: NavHostController,
    selectedGroup: GroupItem?,
) {
    TopAppBar(
        title = {
            if (currentScreen == SharedExpenseScreen.Groups) {
                Text(selectedGroup?.name.toString())

            } else {
                Text(stringResource(currentScreen.title))
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            if (currentScreen == SharedExpenseScreen.Start) {
                IconButton(onClick = { navAccount.navigate(SharedExpenseScreen.Account.name) }) {
                    Icon(
                        painter = painterResource(R.drawable.icon_account),
                        contentDescription = "icon account",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
        }
    )
}

@Composable
fun SharedExpenseApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SharedExpenseScreen.valueOf(
        backStackEntry?.destination?.route ?: SharedExpenseScreen.Start.name
    )
    var selectedGroup by remember { mutableStateOf<GroupItem?>(null) }

    Scaffold(
        topBar = {
            SharedExpenseBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                navAccount = navController,
                selectedGroup = selectedGroup,
            )
        },
        bottomBar = {
            if (currentScreen.name != SharedExpenseScreen.Start.name) {
                BottomTabs(navController = navController, currentRoute = currentScreen.name)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SharedExpenseScreen.Start.name,
        ) {
            val baseModifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
            composable(route = SharedExpenseScreen.Start.name) {
                WelcomeScreen(
                    onAddGroupButtonClicked = {
                        navController.navigate(SharedExpenseScreen.AddGroup.name)
                    },
                    onJoinGroupButtonClicked = {
                        navController.navigate(SharedExpenseScreen.Join.name)
                    },
                    onGroupSelected = { group ->
                        selectedGroup = group
                        navController.navigate(SharedExpenseScreen.Groups.name)
                    },
                    modifier = baseModifier,
                )
            }
            composable(route = SharedExpenseScreen.Groups.name) {
                GroupScreen(
                    selectedGroup = selectedGroup,
                    onAddExpenseButtonClicked = {
                        navController.navigate(SharedExpenseScreen.AddExpense.name)
                    },
                    modifier = baseModifier,
                )
            }
            composable(route = SharedExpenseScreen.AddGroup.name) {
                AddGroupScreen(
                    modifier = baseModifier
                )
            }
            composable(route = SharedExpenseScreen.Join.name) {
                JoinGroupScreen(
                    modifier = baseModifier
                )
            }
            composable(route = SharedExpenseScreen.Account.name) {
                AccountScreen()
            }
            composable(route = SharedExpenseScreen.Balance.name) {
                BalanceScreen(group = selectedGroup, modifier = baseModifier)
            }
            composable(route = SharedExpenseScreen.AddExpense.name) {
                AddExpenseScreen(
                    modifier = baseModifier,
                )
            }
            composable(route = SharedExpenseScreen.Settings.name) {
                SettingsScreen(modifier = baseModifier)
            }
        }
    }
}

@Composable
fun BottomTabs(navController: NavController, currentRoute: String) {
    BottomNavigation {
        screens.forEach { screen ->
            val selected = currentRoute == screen.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painterResource(screen.icon),
                        null,
                    )
                },
                label = { Text(text = screen.label) },
                selectedContentColor = MaterialTheme.colorScheme.inverseOnSurface,
                unselectedContentColor = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
