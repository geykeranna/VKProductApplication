package ru.testtask.vkproductapplication.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import ru.testtask.vkproductapplication.domain.models.Product

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: LazyPagingItems<Product>,
    onClick: (Product) -> Unit
){
    if (handlePagingResults(products = products)){
        LazyVerticalStaggeredGrid(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            items(
                products.itemCount
            ) { index ->
                products[index]?.let { item ->
                    CardItemView(
                        item = item,
                        onClick = {onClick(item)}
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResults(
    products: LazyPagingItems<Product>,
): Boolean {
    val loadState = products.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffectColumn()
            false
        }
        error != null -> {
            EmptyScreen()
            false
        }
        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffectColumn(){
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp)
    ){
        items(10){
            ItemCardShimmerEffect()
        }
    }
}