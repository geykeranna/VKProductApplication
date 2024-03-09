package ru.testtask.vkproductapplication.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.testtask.vkproductapplication.domain.usecases.products.ProductUseCases
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productUseCases: ProductUseCases
): ViewModel() {

    private var _state = mutableStateOf(SearchState())

    val state: State<SearchState> = _state

    private fun searchProducts() {
        val productList = productUseCases.searchProductList(searchQuery = _state.value.searchQuery).cachedIn(viewModelScope)
        _state.value = _state.value.copy(productList = productList)
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.Search -> {
                searchProducts()
            }
        }
    }
}