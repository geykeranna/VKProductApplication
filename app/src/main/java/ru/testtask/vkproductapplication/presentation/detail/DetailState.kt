package ru.testtask.vkproductapplication.presentation.detail

import kotlinx.coroutines.flow.Flow
import ru.testtask.vkproductapplication.domain.models.Product

data class DetailState(
    val id: Int = 0,
    val product: Flow<Product>? = null
)