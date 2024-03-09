package ru.testtask.vkproductapplication.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.testtask.vkproductapplication.presentation.navigation.Route

class MainViewModel: ViewModel() {
    var startDestination by mutableStateOf(Route.HomeScreen.route)
        private set
}