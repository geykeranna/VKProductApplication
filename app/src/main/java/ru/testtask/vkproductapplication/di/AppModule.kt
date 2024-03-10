package ru.testtask.vkproductapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.testtask.vkproductapplication.data.remote.ProductsApi
import ru.testtask.vkproductapplication.data.repository.ProductsRepositoryImpl
import ru.testtask.vkproductapplication.domain.repository.ProductRepository
import ru.testtask.vkproductapplication.domain.usecases.products.GetOneProduct
import ru.testtask.vkproductapplication.domain.usecases.products.GetProductList
import ru.testtask.vkproductapplication.domain.usecases.products.ProductUseCases
import ru.testtask.vkproductapplication.domain.usecases.products.SearchProductList
import ru.testtask.vkproductapplication.utils.Constants.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesProductsApi(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesProductRepository(
        productsApi: ProductsApi
    ): ProductRepository = ProductsRepositoryImpl(productsApi)

    @Provides
    @Singleton
    fun provideProductsUseCases(
        productRepository: ProductRepository
    ): ProductUseCases{
        return ProductUseCases(
            getProductList = GetProductList(productRepository),
            searchProductList = SearchProductList(productRepository),
            getOneProduct = GetOneProduct(productRepository)
        )
    }
}