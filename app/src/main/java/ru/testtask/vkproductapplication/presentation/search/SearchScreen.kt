package ru.testtask.vkproductapplication.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.presentation.components.ProductsList
import ru.testtask.vkproductapplication.presentation.components.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {
                event(SearchEvent.Search)
            }
        )

        state.productList?.let {
            ProductsList(
                products = it.collectAsLazyPagingItems(),
                onClick = navigateToDetails
            )
        }
    }

}