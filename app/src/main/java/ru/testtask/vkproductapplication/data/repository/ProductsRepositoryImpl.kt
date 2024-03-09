package ru.testtask.vkproductapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.vkproductapplication.data.remote.ProductPagingSource
import ru.testtask.vkproductapplication.data.remote.ProductsApi
import ru.testtask.vkproductapplication.data.remote.SearchPagingSource
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.domain.repository.ProductRepository
import ru.testtask.vkproductapplication.utils.Constants

class ProductsRepositoryImpl(
    private val productsApi: ProductsApi
) : ProductRepository {
    private val limit: Int = Constants.DEFAULT_LIMIT_ON_PAGE

    override fun getProductList(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = {
                ProductPagingSource(
                    productsApi = productsApi,
                )
            }
        ).flow
    }

    override fun getProductListBySearch(searchQuery: String): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = productsApi,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }
}