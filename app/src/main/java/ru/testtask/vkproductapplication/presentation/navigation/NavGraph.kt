package ru.testtask.vkproductapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import ru.testtask.vkproductapplication.presentation.home.HomeScreen
import ru.testtask.vkproductapplication.presentation.home.HomeScreenViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = "HomeScreen"){
            val viewModel: HomeScreenViewModel = hiltViewModel()
            HomeScreen(
                productsList = viewModel.productList.collectAsLazyPagingItems(),
                navigate = { navigate(navController = navController, route = it) }
            )
        }

        navigation(
            route = Route.DetailScreen.route,
            startDestination = Route.HomeScreen.route
        ){
            //
        }
    }
}

private fun navigate(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}