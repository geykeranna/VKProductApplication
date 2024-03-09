package ru.testtask.vkproductapplication.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.ui.theme.VKProductApplicationTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardItemView(
    item: Product,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.Gray, RoundedCornerShape(20.dp))
            .clickable { onClick?.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AsyncImage(
            model = item.thumbnail,
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .size(140.dp)
                .padding(5.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .basicMarquee()
                .padding(horizontal = 6.dp),
            text = item.title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .basicMarquee()
                .padding(horizontal = 6.dp, vertical = 4.dp),
            text = "${item.price} $",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
    }
}