package ru.testtask.vkproductapplication.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import ru.testtask.vkproductapplication.R
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.presentation.components.ProductsList
import ru.testtask.vkproductapplication.presentation.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    navigateToDetails: (Product) -> Unit,
    productsList: LazyPagingItems<Product>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp)
            .padding(horizontal = 10.dp)
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .wrapContentWidth()

                    )

                    SearchBar(
                        modifier = Modifier,
                        text = "",
                        readOnly = true,
                        onValueChange = {},
                        onClick = navigateToSearch,
                        onSearch = {}
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
            )
        )

        ProductsList(
            modifier = Modifier.padding(top = 20.dp),
            products = productsList,
            onClick = navigateToDetails
        )
    }
}