package ru.testtask.vkproductapplication.domain.usecases.products

import kotlinx.coroutines.flow.Flow
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.domain.repository.ProductRepository

class GetOneProduct (
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: Int): Flow<Product> {
        return productRepository.getOneProduct(id)
    }
}