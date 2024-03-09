package ru.testtask.vkproductapplication.data.remote.dto

import ru.testtask.vkproductapplication.domain.models.Product

data class ProductsGetResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)