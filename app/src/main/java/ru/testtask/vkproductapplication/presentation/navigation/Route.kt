package ru.testtask.vkproductapplication.presentation.navigation

sealed class Route(
    val route: String
) {
    data object HomeScreen: Route(route = "homeScreen")
    data object DetailScreen: Route(route = "detailScreen")
    data object SearchScreen: Route(route = "searchScreen")

    data object AppStartNavigation : Route(route = "appStartNavigation")

    data object Navigation: Route(route = "navigation")

    data object NavigatorScreen: Route(route = "navigator")
}