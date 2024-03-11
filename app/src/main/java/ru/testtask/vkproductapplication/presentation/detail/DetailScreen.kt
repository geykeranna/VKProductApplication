package ru.testtask.vkproductapplication.presentation.detail

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.StateFlow
import ru.testtask.vkproductapplication.R
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.presentation.components.EmptyScreen
import ru.testtask.vkproductapplication.ui.theme.AccentColor
import ru.testtask.vkproductapplication.ui.theme.Tertiary
import ru.testtask.vkproductapplication.ui.theme.TextColorDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    item: StateFlow<Product>,
    state: LoadState,
    navigateBack: () -> Unit
) {
    if (state is LoadState.Error) EmptyScreen()
    else {
        val data = item.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = { },
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 14.dp)
            ) {
                Text(
                    text = data.brand,
                    fontSize = 24.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 20.dp)
                )
                Text(
                    text = data.title,
                    fontSize = 36.sp,
                    lineHeight = 38.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 20.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 4.dp),
                        imageVector = Icons.Default.Star,
                        contentDescription = "rating",
                        tint = if(isSystemInDarkTheme()) AccentColor else TextColorDark
                    )
                    Text(text = data.rating.toString())
                }
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(10.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(data.images.size) {
                    AsyncImage(
                        model = data.images[it],
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .size(400.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 25.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 5.dp),
                    text = stringResource(R.string.description_label),
                    fontSize = 22.sp
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    text = data.description,
                    overflow = TextOverflow.Visible,
                    fontSize = 20.sp
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 20.dp),
                    text = "В наличии: ${data.stock}",
                    fontSize = 16.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 20.dp)
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    fontSize = 34.sp,
                    textAlign = TextAlign.Start,
                    text = "${data.price} $"
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .wrapContentSize(),
                    colors = ButtonDefaults.buttonColors(Tertiary)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        fontSize = 14.sp,
                        text = stringResource(id = R.string.add_at_button_label)
                    )
                }
            }
        }
    }
}