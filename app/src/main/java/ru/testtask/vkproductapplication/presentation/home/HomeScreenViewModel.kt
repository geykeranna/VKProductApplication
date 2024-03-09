package ru.testtask.vkproductapplication.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.testtask.vkproductapplication.domain.usecases.products.ProductUseCases
import ru.testtask.vkproductapplication.presentation.navigation.Route
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val productUseCases: ProductUseCases
): ViewModel() {
    var startDestination by mutableStateOf(Route.HomeScreen.route)
        private set

    val productList = productUseCases.getProductList().cachedIn(viewModelScope)
}