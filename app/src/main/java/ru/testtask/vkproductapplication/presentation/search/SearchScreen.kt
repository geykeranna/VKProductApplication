package ru.testtask.vkproductapplication.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import ru.testtask.vkproductapplication.R
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.presentation.components.ProductsList
import ru.testtask.vkproductapplication.presentation.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Product) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        TopAppBar(
            title = {
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
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back button"
                    )
                }
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