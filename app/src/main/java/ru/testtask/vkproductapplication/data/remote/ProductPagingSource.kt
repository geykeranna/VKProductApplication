package ru.testtask.vkproductapplication.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.utils.Constants.DEFAULT_LIMIT_ON_PAGE

class ProductPagingSource(
    private val productsApi: ProductsApi
): PagingSource<Int, Product>() {
    private val limit: Int = DEFAULT_LIMIT_ON_PAGE
    private var totalCount = 0

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        var page = params.key ?: 1
        return try {
            val prodResponse = productsApi.getProducts(limit = limit, skip = (page - 1) * limit)
            Log.d("ntw", prodResponse.limit.toString())
            Log.d("ntw", prodResponse.skip.toString())
            totalCount += prodResponse.products.size
            LoadResult.Page(
                data = prodResponse.products,
                nextKey = if (totalCount >= prodResponse.total) null else ++page,
                prevKey = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}