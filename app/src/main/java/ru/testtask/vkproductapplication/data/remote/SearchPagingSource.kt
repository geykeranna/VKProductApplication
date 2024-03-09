package ru.testtask.vkproductapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.testtask.vkproductapplication.domain.models.Product

class SearchPagingSource(
    private val api: ProductsApi,
    private val searchQuery: String
): PagingSource<Int, Product>() {
    private var totalCount = 0
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 1

        return try {
            val searchResponse = api.searchProducts(searchQuery = searchQuery)
            totalCount += searchResponse.products.size

            LoadResult.Page(
                data = searchResponse.products,
                nextKey = if (totalCount <= searchResponse.total) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}