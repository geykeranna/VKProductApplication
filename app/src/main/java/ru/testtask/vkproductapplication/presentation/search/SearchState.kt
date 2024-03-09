package ru.testtask.vkproductapplication.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.vkproductapplication.domain.models.Product

data class SearchState(
    val searchQuery: String = "",
    val productList: Flow<PagingData<Product>>? = null
)