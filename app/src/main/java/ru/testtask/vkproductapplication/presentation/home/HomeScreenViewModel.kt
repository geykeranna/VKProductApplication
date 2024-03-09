package ru.testtask.vkproductapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.testtask.vkproductapplication.domain.usecases.products.ProductUseCases
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    productUseCases: ProductUseCases
): ViewModel() {

    val productList = productUseCases.getProductList().cachedIn(viewModelScope)
}