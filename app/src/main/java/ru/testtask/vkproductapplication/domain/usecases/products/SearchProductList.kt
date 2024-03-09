package ru.testtask.vkproductapplication.domain.usecases.products

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.domain.repository.ProductRepository

class SearchProductList (
    private val productRepository: ProductRepository
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Product>> {
        return productRepository.getProductListBySearch(searchQuery)
    }
}