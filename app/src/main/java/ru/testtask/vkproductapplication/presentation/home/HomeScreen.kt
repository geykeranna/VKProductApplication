package ru.testtask.vkproductapplication.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import ru.testtask.vkproductapplication.R
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.presentation.components.ProductsList
import ru.testtask.vkproductapplication.presentation.components.SearchBar

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Product) -> Unit,
    productsList: LazyPagingItems<Product>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {

        Text(
            text = stringResource(R.string.app_name),
            fontSize = 36.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .padding(bottom = 20.dp, top = 26.dp)
        )

        SearchBar(
            modifier = Modifier,
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = navigateToSearch,
            onSearch = {}
        )

        ProductsList(
            modifier = Modifier,
            products = productsList,
            onClick = navigateToDetails
        )
    }
}