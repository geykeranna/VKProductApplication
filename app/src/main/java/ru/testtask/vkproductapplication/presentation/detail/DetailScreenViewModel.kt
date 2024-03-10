package ru.testtask.vkproductapplication.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.domain.usecases.products.ProductUseCases
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val productUseCases: ProductUseCases
): ViewModel() {
    val productData: StateFlow<Product>
        get() = _data.asStateFlow()

    private val _data = MutableStateFlow(Product.shimmerData)

    private fun getOneProduct(id: Int) = viewModelScope.launch {
        _data.emit(productUseCases.getOneProduct(id = id).last())
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetOneProduct -> {
                getOneProduct(event.id)
            }
        }
    }
}