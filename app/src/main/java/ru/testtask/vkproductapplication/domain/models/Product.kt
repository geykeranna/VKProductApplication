package ru.testtask.vkproductapplication.domain.models

data class Product(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
){
    companion object {
        val shimmerData = Product(
            brand = "",
            category = "",
            description = "",
            discountPercentage = 0.0,
            id = 0,
            images = listOf(),
            price = 0,
            rating = 0.0,
            stock = 0,
            thumbnail = "",
            title = ""
        )
    }
}