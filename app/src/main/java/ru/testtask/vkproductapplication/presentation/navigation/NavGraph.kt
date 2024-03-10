package ru.testtask.vkproductapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import ru.testtask.vkproductapplication.domain.models.Product
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
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.HomeScreen.route
        ){
            composable(route = Route.HomeScreen.route){
                val viewModel: HomeScreenViewModel = hiltViewModel()
                HomeScreen(
                    productsList = viewModel.productList.collectAsLazyPagingItems(),
                    navigateToSearch = { navigate(navController, Route.SearchScreen.route) },
                    navigateToDetails = {item ->
                        navigateToDetails(
                            navController = navController,
                            item = item
                        )
                    }
                )
            }
        }

        navigation(
            route = Route.Navigation.route,
            startDestination = Route.NavigatorScreen.route
        ) {
            composable(route = Route.NavigatorScreen.route){
                Navigator()
            }
        }
    }
}

fun navigate(navController: NavController, route: String) {
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
fun navigateToDetails(navController: NavController, item: Product){
    navController.currentBackStackEntry?.savedStateHandle?.set("id_product", item.id)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}