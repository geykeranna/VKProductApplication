package ru.testtask.vkproductapplication.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.testtask.vkproductapplication.data.remote.dto.ProductsGetResponse

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
    ): ProductsGetResponse

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") searchQuery: String
    ): ProductsGetResponse
}