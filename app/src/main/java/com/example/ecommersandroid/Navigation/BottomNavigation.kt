package com.example.ecommersandroid.Navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommersandroid.screens.AddressScreen
import com.example.ecommersandroid.screens.ProfileScreen
import com.example.ecommersandroid.screens.forgot.ForgotScreen
import com.example.ecommersandroid.screens.shoping.CartScreen
import com.example.ecommersandroid.screens.shoping.HomeScreen
import com.example.ecommersandroid.screens.shoping.SettingScreen
import com.example.ecommersandroid.screens.shoping.ShopCategories

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    var selectedNavigationIndex by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex == index,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedNavigationIndex = index
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
//                label = { Text(text = item.title) }
            )
        }
    }
}


data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = Screen.Home.route
    ),
    NavigationItem(
        title = "Search",
        icon = Icons.Default.Search,
        route = Screen.Search.route
    ),
    NavigationItem(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = Screen.Cart.route
    ),
    NavigationItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = Screen.Profile.route
    )
)
@Composable
fun BottomNavScaffold(rootNavController: NavHostController) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    fun NavController.navigateBack(onIsLastComposable: () -> Unit = {}) {
      bottomNavController.navigateBack(onIsLastComposable)
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute != Screen.Cart.route) {
                BottomNavigationBar(navController = bottomNavController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = rootNavController)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = rootNavController)
            }
            composable(Screen.Cart.route) {
                CartScreen(navController = rootNavController)
            }
            composable(Screen.Search.route) {
                SettingScreen()
            }
        }
    }
}


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = BottomNavigationScreen.BottomNav.route
    ) {
        // Bottom tab navigation
        composable(BottomNavigationScreen.BottomNav.route) {
            BottomNavScaffold(rootNavController = rootNavController)
        }

        // Outside screen
        composable(Screen.ListCategaries.route) {
            ShopCategories(navController = rootNavController)
        }
        composable(NaivgationScreenConst.Forgot.route) {
            ForgotScreen(navController = rootNavController)
        }
        composable(Screen.Address.route) {
            AddressScreen(navController = rootNavController)
        }
    }
}

