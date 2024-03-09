package ru.testtask.vkproductapplication.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import ru.testtask.vkproductapplication.presentation.home.HomeScreen
import ru.testtask.vkproductapplication.presentation.home.HomeScreenViewModel
import ru.testtask.vkproductapplication.presentation.search.SearchScreen
import ru.testtask.vkproductapplication.presentation.search.SearchViewModel

@Composable
fun Navigator() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ){
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding),
        ) {
            composable(route = Route.HomeScreen.route){
                val viewModel: HomeScreenViewModel = hiltViewModel()
                HomeScreen(
                    productsList = viewModel.productList.collectAsLazyPagingItems(),
                    navigate = { navigate(navController = navController, route = it) }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                )
            }
            composable(route = Route.DetailScreen.route) {

            }
        }
    }
}


@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigate(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}
