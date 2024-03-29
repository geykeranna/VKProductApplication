package ru.testtask.vkproductapplication.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.testtask.vkproductapplication.R
import ru.testtask.vkproductapplication.domain.models.Product
import ru.testtask.vkproductapplication.ui.theme.Tertiary
import ru.testtask.vkproductapplication.ui.theme.TextColorDark

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
            .wrapContentHeight()
            .padding(5.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Tertiary, RoundedCornerShape(20.dp))
            .clickable { onClick?.invoke() },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (item.discountPercentage > 0){
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 14.dp, start = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Tertiary),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    modifier = Modifier
                        .padding(horizontal = 4.dp),
                    text = "${item.discountPercentage}%",
                    fontSize = 12.sp,
                    color = TextColorDark
                )
            }
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
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

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 6.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(Tertiary)
            ) {
                Text(
                    modifier = Modifier,
                    fontSize = 12.sp,
                    text = stringResource(id = R.string.add_button_label)
                )
            }
        }
    }
}