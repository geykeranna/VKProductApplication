package ru.testtask.vkproductapplication.presentation.navigation

sealed class Route(
    val route: String
) {
    data object HomeScreen: Route(route = "homeScreen")
    data object DetailScreen: Route(route = "detailScreen")
    data object SearchScreen: Route(route = "searchScreen")
}