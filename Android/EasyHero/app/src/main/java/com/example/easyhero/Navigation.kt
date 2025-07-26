package com.example.easyhero

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.easyhero.favorites.presentation.FavoritesView
import com.example.easyhero.home.Hero
import com.example.easyhero.home.presentation.HeroDetailView
import com.example.easyhero.home.presentation.HomeView
import com.example.easyhero.home.presentation.PresentationModule

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val homeViewModel = remember { PresentationModule.getHomeViewModel() }
    val favoritesViewModel = remember { PresentationModule.getFavoritesViewModel() }

    val navigationItems = listOf(
        NavigationItem(
            name = "Home",
            selectedIcon = Icons.Default.Home,
            icon = Icons.Outlined.Home,
            route = "home"
        ),
        NavigationItem(
            name = "Favorites",
            selectedIcon = Icons.Default.Favorite,
            icon = Icons.Outlined.FavoriteBorder,
            route = "favorites"
        )
    )

    val selectedHero = remember { mutableStateOf<Hero?>(null) }
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (currentRoute == item.route) item.selectedIcon else item.icon,
                                contentDescription = item.name
                            )
                        },
                        label = { Text(item.name) }
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") {
                HomeView(viewModel = homeViewModel) { hero ->
                    selectedHero.value = hero
                    navController.navigate("hero_detail")
                }
            }

            composable("hero_detail") {
                selectedHero.value?.let { hero ->
                    HeroDetailView(hero = hero)
                }
            }

            composable("favorites") {
                FavoritesView(viewModel = favoritesViewModel)
            }
        }
    }
}

data class NavigationItem(
    val name: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
)