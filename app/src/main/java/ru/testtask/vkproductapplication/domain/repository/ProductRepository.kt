package ru.testtask.vkproductapplication.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.testtask.vkproductapplication.domain.models.Product

interface ProductRepository {

    fun getProductList(): Flow<PagingData<Product>>
}