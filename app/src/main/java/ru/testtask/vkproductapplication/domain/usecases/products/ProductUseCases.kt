package ru.testtask.vkproductapplication.domain.usecases.products

data class ProductUseCases(
    val getProductList: GetProductList,
    val searchProductList: SearchProductList,
    val getOneProduct: GetOneProduct
)