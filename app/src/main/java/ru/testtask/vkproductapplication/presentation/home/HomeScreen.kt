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
import ru.testtask.vkproductapplication.presentation.navigation.Route

@Composable
fun HomeScreen(
    navigate:(String) -> Unit,
    productsList: LazyPagingItems<Product>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(R.string.app_name),
            fontSize = 36.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 18.dp, top = 26.dp)
        )

        SearchBar(
            modifier = Modifier.padding(10.dp),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigate(Route.SearchScreen.route)
            },
            onSearch = {}
        )

        ProductsList(
            modifier = Modifier,
            products = productsList,
            onClick = {
                navigate(Route.DetailScreen.route)
            }
        )
    }
}