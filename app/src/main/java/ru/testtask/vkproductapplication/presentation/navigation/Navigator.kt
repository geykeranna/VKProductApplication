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
import ru.testtask.vkproductapplication.presentation.detail.DetailEvent
import ru.testtask.vkproductapplication.presentation.detail.DetailScreen
import ru.testtask.vkproductapplication.presentation.detail.DetailScreenViewModel
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
                    navigateToSearch = {
                        navigate(navController, Route.SearchScreen.route)
                    },
                    navigateToDetails = {item ->
                        navigateToDetails(
                            navController = navController,
                            item = item
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetails = {item ->
                        navigateToDetails(
                            navController = navController,
                            item = item
                        )
                    }
                )
            }
            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailScreenViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Int?>("id_product")
                    ?.let {item ->
                        viewModel.onEvent(DetailEvent.GetOneProduct(item))
                        DetailScreen(
                            item = viewModel.productData,
                            navigateBack = { navController.navigateUp() }
                        )
                    }
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
